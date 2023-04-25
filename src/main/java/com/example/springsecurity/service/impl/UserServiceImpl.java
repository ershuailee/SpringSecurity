package com.example.springsecurity.service.impl;

import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.dto.AuthRequestDTO;
import com.example.springsecurity.vo.AuthResponseVO;
import com.example.springsecurity.service.JWTService;
import com.example.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-01
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JWTService jwtService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponseVO login(AuthRequestDTO requestVO) {

        String username = requestVO.getUsername();
        String password = requestVO.getPassword();

        // 进行用户认证
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            authentication.getPrincipal();
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCodes.PASSWORD_ERROR);
        }

        // 认证通过生成token
        String token = jwtService.generateToken(username);


        AuthResponseVO responseVO = new AuthResponseVO();
        responseVO.setUsername(username);
        responseVO.setToken(token);

        return responseVO;
    }


    public void authenticate(String username, String password) {

    }


}
