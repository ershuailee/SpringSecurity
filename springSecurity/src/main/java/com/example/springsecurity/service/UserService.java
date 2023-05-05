package com.example.springsecurity.service;

import com.example.springsecurity.dto.AuthRequestDTO;
import com.example.springsecurity.dto.UserRegisterDTO;
import com.example.springsecurity.entity.user.PermissionEntity;
import com.example.springsecurity.vo.AuthResponseVO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-01
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param user 用户注册信息
     */
    void register(UserRegisterDTO user);

    /**
     * 用户登录
     *
     * @param requestVO 请求参数
     * @return 登录数据
     */
    AuthResponseVO login(AuthRequestDTO requestVO);

    /**
     * 获取用户权限列表
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<PermissionEntity> getPermissions(Long userId);
}
