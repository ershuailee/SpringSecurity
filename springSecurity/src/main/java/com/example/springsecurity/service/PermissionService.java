package com.example.springsecurity.service;

import com.example.springsecurity.dto.PermissionDTO;
import com.example.springsecurity.vo.PermissionTreeVO;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
public interface PermissionService {

    /**
     * 新增权限数据
     *
     * @param dto 请求数据
     */
    void insertPermission(PermissionDTO dto);

    /**
     * 获取全部权限数据
     *
     * @return 权限树
     */
    List<PermissionTreeVO> listAllPermissions();

    /**
     * 获取用户权限列表
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<PermissionTreeVO> listPermissionsByUserId(Long userId);
}
