package com.example.springsecurity.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.springsecurity.constants.ErrorCodeConstant;
import com.example.springsecurity.entity.common.BaseResponseEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录处理
 *
 * @author 李二帅
 * @since 2023/4/25 11:02
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        BaseResponseEntity<Object> baseResponseEntity = new BaseResponseEntity<>(ErrorCodeConstant.INVALID_TOKEN,
                BusinessErrorCodes.INVALID_TOKEN.getMessage(), null);
        response.getWriter().write(JSON.toJSONString(baseResponseEntity));
        response.getWriter().flush();

    }
}