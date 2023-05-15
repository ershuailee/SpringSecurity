package com.example.springsecurity.service;

import com.example.springsecurity.pojo.entity.RoleEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
public interface RoleService {

    /**
     * 角色表新增
     *
     * @param param 根据需要进行传值
     * @return
     */
    void add(RoleEntity param);

    /**
     * 角色表修改
     *
     * @param param 根据需要进行传值
     * @return
     */
    void updateById(RoleEntity param);

    /**
     * 角色表删除(单个条目)
     *
     * @param id
     * @return
     */
    void removeById(Long id);

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    RoleEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<RoleEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<RoleEntity> listByConditions(RoleEntity conditions);

    /**
     * 根据条件查询数据数量
     *
     * @param conditions 查询条件
     * @return 查询到的数据数量
     */
    int countByConditions(RoleEntity conditions);

    /**
     * 通过用户ID查询用户角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<RoleEntity> getRoleByUserId(Long userId);
}
