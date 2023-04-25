package com.example.springsecurity.config;

import com.alibaba.fastjson.JSON;
import com.example.springsecurity.constants.ErrorCodeConstant;
import com.example.springsecurity.entity.ApiResult;
import com.example.springsecurity.enums.BusinessErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserAuthenticationEntryPoint
 *
 * @author 李二帅
 * @since 2023/4/25 11:02
 */
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        // ApiResult<T> apiResult = new ApiResult<>(ErrorCodeConstant.INVALID_TOKEN,
        //         BusinessErrorCodes.INVALID_TOKEN.getMessage(), null);

        // response.getWriter().write(JSON.toJSONString(apiResult));
        response.getWriter().flush();

    }
}