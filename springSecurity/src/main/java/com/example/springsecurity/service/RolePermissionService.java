package com.example.springsecurity.service;

import com.example.springsecurity.pojo.dto.RolePermissionDTO;
import com.example.springsecurity.pojo.entity.RolePermissionEntity;
import com.example.springsecurity.pojo.vo.RolePermissionVO;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
public interface RolePermissionService {

    /**
     * 角色新增权限
     *
     * @param dto 请求数据
     */
    void addRolePermission(RolePermissionDTO dto);

    /**
     * 根据角色ID查询角色权限
     *
     * @param id 角色ID
     * @return 权限数据
     */
    List<RolePermissionVO> getRolePermission(Long id);

    /**
     * 通过角色ID查询按钮权限
     *
     * @param roleIds 角色ID列表
     * @return 角色按钮列表
     */
    List<RolePermissionEntity> listByRoleIds(List<Long> roleIds);

}
