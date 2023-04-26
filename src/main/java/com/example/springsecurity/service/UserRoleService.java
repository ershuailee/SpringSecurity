package com.example.springsecurity.service;

import com.example.springsecurity.entity.user.UserRoleEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
public interface UserRoleService {

    /**
     * 用户角色关联表新增
     *
     * @param param 根据需要进行传值
     */
    void add(UserRoleEntity param);

    /**
     * 用户角色关联表修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(UserRoleEntity param);

    /**
     * 用户角色关联表删除(单个条目)
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
    UserRoleEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<UserRoleEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<UserRoleEntity> listByConditions(UserRoleEntity conditions);

    /**
     * 根据条件查询数据数量
     *
     * @param conditions 查询条件
     * @return 查询到的数据数量
     */
    Long countByConditions(UserRoleEntity conditions);

    /**
     * 通过用户ID查询角色ID
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    List<UserRoleEntity> getRoleIdByUserId(Long userId);
}
