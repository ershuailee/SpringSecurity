package com.example.springsecurity.service;

import com.example.springsecurity.entity.user.MenuEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
public interface MenuService {

    /**
     * 菜单表新增
     *
     * @param param 根据需要进行传值
     */
    void add(MenuEntity param);

    /**
     * 菜单表修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(MenuEntity param);

    /**
     * 菜单表删除(单个条目)
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
    MenuEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<MenuEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<MenuEntity> listByConditions(MenuEntity conditions);

    /**
     * 根据条件查询数据数量
     *
     * @param conditions 查询条件
     * @return 查询到的数据数量
     */
    Long countByConditions(MenuEntity conditions);

    /**
     * 通过角色ID列表查询角色数据
     *
     * @param roleIds 角色ID列表
     * @return 角色按钮权限
     */
    List<MenuEntity> getUserMenuByRoleId(List<Long> roleIds);
}
