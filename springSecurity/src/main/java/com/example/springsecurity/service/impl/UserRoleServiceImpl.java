package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.batchmapper.UserRoleBatchMapper;
import com.example.springsecurity.pojo.entity.UserRoleEntity;
import com.example.springsecurity.common.enums.BusinessErrorCodes;
import com.example.springsecurity.common.exception.BusinessException;
import com.example.springsecurity.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleBatchMapper userRoleBatchMapper;

    /**
     * 用户角色关联表新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserRoleEntity param) {

        if (!userRoleBatchMapper.save(param)) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 用户角色关联表修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(UserRoleEntity param) {
        if (!userRoleBatchMapper.updateById(param)) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 用户角色关联表删除(单个条目)
     *
     * @param id 数据ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (!userRoleBatchMapper.removeById(id)) {
            throw new BusinessException(BusinessErrorCodes.DELETE_FAILED);
        }
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    @Override
    public UserRoleEntity selectById(Long id) {
        if (null == id) {
            return null;
        }

        return userRoleBatchMapper.getById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<UserRoleEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<UserRoleEntity> entities = userRoleBatchMapper.listByIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<UserRoleEntity> listByConditions(UserRoleEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<UserRoleEntity> queryWrapper = getConditionsByEntity(conditions);

        List<UserRoleEntity> entities = userRoleBatchMapper.list(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据数量
     *
     * @param conditions 查询条件
     * @return 查询到的数据数量
     */
    @Override
    public Long countByConditions(UserRoleEntity conditions) {
        if (null == conditions) {
            return 0L;
        }

        QueryWrapper<UserRoleEntity> queryWrapper = getConditionsByEntity(conditions);

        return userRoleBatchMapper.count(queryWrapper);
    }

    /**
     * 通过用户ID查询角色ID
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    @Override
    public List<UserRoleEntity> getRoleIdByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<>();
        }

        LambdaQueryWrapper<UserRoleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoleEntity::getUserId, userId);
        return userRoleBatchMapper.list(wrapper);
    }


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<UserRoleEntity> getConditionsByEntity(UserRoleEntity param) {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 用户id
                .eq(param.getUserId() != null, UserRoleEntity::getUserId, param.getUserId())
                // 角色id
                .eq(param.getRoleId() != null, UserRoleEntity::getRoleId, param.getRoleId())
        ;
        return queryWrapper;
    }

}
