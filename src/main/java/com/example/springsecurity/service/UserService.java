package com.example.springsecurity.service;

import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.dto.AuthRequestDTO;
import com.example.springsecurity.vo.AuthResponseVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-01
 */
public interface UserService {


    AuthResponseVO login(AuthRequestDTO requestVO);

    void authenticate(String username, String password);
}
