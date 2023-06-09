package com.example.springsecurity.service.impl;

import com.example.springsecurity.common.exception.BusinessException;
import com.example.springsecurity.propertie.AuthProperties;
import com.example.springsecurity.service.JWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * JWTServiceImpl
 *
 * @author 李二帅
 * @since 2023/4/25 11:03
 */

@Slf4j
@Service
public class JWTServiceImpl implements JWTService {

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    private final AuthProperties properties;

    public JWTServiceImpl(AuthProperties properties) {
        this.properties = properties;
    }

    @Override
    public String generateToken(String username) {
        Date date =
                new Date(new Date().getTime() + properties.getJwt().getClaims().getExpirationTimeMinutes() * 60 * 1000);
        return Jwts.builder()
                .setIssuer(properties.getJwt().getClaims().getIssuer())
                .setSubject(username)
                .setAudience(properties.getJwt().getClaims().getAudience())
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(SIGNATURE_ALGORITHM, properties.getJwt().getSecret())
                .compact();
    }

    @Override
    public String validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(properties.getJwt().getSecret())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (BusinessException e) {
            return null;
        }
    }

    /**
     * 签名查询
     *
     * @param request HttpServletRequest
     * @return token
     */
    @Override
    public String getToken(HttpServletRequest request) {
        String header = request.getHeader(properties.getJwt().getAuthHeader());
        // 截取token部分
        return header == null ? null : header.substring(header.indexOf(" ") + 1);
    }

}