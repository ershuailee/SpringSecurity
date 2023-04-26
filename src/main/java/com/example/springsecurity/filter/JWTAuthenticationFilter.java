package com.example.springsecurity.filter;

import com.alibaba.fastjson2.JSON;
import com.example.springsecurity.constants.ErrorCodeConstant;
import com.example.springsecurity.entity.common.ResultEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.entity.common.UserInfo;
import com.example.springsecurity.service.JWTService;
import com.example.springsecurity.service.impl.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWTAuthenticationFilter
 *
 * @author 李二帅
 * @since 2023/4/25 11:02
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private final SysUserService userService;
    private final JWTService jwtService;
    private final UserCache userCache;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SysUserService userService,
                                   JWTService jwtService, UserCache userCache) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtService = jwtService;
        this.userCache = userCache;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = jwtService.getToken(request);
        String tokenHeader = request.getHeader("Authorization");
        // 如果请求头中没有Authorization信息则直接放行了
        if (!StringUtils.hasLength(tokenHeader)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        String username = jwtService.validateToken(token);
        if (!StringUtils.hasLength(username)) {
            chain.doFilter(request, response);
            return;
        }

        // 从缓存中验证token的存在性
        UserInfo user = (UserInfo) userCache.getUserFromCache(username);
        if (null == user) {
            try {
                user = (UserInfo) userService.loadUserByUsername(username);
                userCache.putUserInCache(user);
            } catch (UsernameNotFoundException e) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.OK.value());
                ResultEntity<Object> resultEntity = new ResultEntity<>(ErrorCodeConstant.INTERNAL_SERVER_ERROR,
                        BusinessErrorCodes.DEFAULT_BUSINESS_ERROR.getMessage(), null);
                response.getWriter().write(JSON.toJSONString(resultEntity));
                response.getWriter().flush();
                return;
            }
        }

        // 如果从持久化存储中仍未查到，则执行后续操作，最后返回用户不存在信息到前端
        if (null != user) {
            // 清空“密码”属性
            // 创建验证通过的令牌对象
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities());
            // 设置令牌到安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }
}