package com.example.springsecurity.service;

import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.info.AuthRequestVO;
import com.example.springsecurity.info.AuthResponseVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-01
 */
public interface UserService {


    AuthResponseVO login(AuthRequestVO requestVO) throws BusinessException;

    void authenticate(String username, String password) throws BusinessException;
}
