package com.example.springsecurity.service;

import com.example.springsecurity.dto.PermissionDTO;
import com.example.springsecurity.entity.user.PermissionEntity;
import com.example.springsecurity.vo.PermissionVO;

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
     * 新增权限
     *
     * @param dto 请求参数
     */
    void addPermission(PermissionDTO dto);

    /**
     * 获取权限树
     *
     * @return 权限树
     */
    List<PermissionVO> getPermission();

    /**
     * 通过角色ID列表查询角色数据
     *
     * @param roleIds 角色ID列表
     * @return 角色按钮权限
     */
    List<PermissionEntity> getUserMenuByRoleId(List<Long> roleIds);



}
