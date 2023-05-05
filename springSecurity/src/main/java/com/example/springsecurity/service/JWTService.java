package com.example.springsecurity.service;

import javax.servlet.http.HttpServletRequest;

public interface JWTService {

    /**
     * 签名生成
     *
     * @param username 用户名
     * @return token
     */
    String generateToken(String username);

    /**
     * 签名检验
     *
     * @param token token
     * @return username
     */
    String validateToken(String token);

    /**
     * 签名查询
     *
     * @param request HttpServletRequest
     * @return token
     */
    String getToken(HttpServletRequest request);
}
