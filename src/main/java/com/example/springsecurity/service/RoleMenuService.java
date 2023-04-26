package com.example.springsecurity.service;

import com.example.springsecurity.entity.user.RoleMenuEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
public interface RoleMenuService {

    /**
     * 角色菜单关联表新增
     *
     * @param param 根据需要进行传值
     */
    void add(RoleMenuEntity param);

    /**
     * 角色菜单关联表修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(RoleMenuEntity param);

    /**
     * 角色菜单关联表删除(单个条目)
     *
     * @param id 数据ID
     */
    void removeById(Long id);

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    RoleMenuEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<RoleMenuEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<RoleMenuEntity> listByConditions(RoleMenuEntity conditions);

    /**
     * 根据条件查询数据数量
     *
     * @param conditions 查询条件
     * @return 查询到的数据数量
     */
    Long countByConditions(RoleMenuEntity conditions);

    /**
     * 通过角色ID查询按钮权限
     *
     * @param roleIds 角色ID列表
     * @return 角色按钮列表
     */
    List<RoleMenuEntity> listByRoleIds(List<Long> roleIds);
}
