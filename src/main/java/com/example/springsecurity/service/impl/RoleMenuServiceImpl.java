package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.batchmapper.RoleMenuBatchMapper;
import com.example.springsecurity.entity.user.RoleMenuEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Slf4j
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    private RoleMenuBatchMapper roleMenuBatchMapper;


    /**
     * 角色菜单关联表新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleMenuEntity param) {

        if (!roleMenuBatchMapper.save(param)) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 角色菜单关联表修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(RoleMenuEntity param) {
        if (!roleMenuBatchMapper.updateById(param)) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 角色菜单关联表删除(单个条目)
     *
     * @param id 数据ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (!roleMenuBatchMapper.removeById(id)) {
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
    public RoleMenuEntity selectById(Long id) {
        if (null == id) {
            return null;
        }

        return roleMenuBatchMapper.getById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<RoleMenuEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<RoleMenuEntity> entities = roleMenuBatchMapper.listByIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<RoleMenuEntity> listByConditions(RoleMenuEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<RoleMenuEntity> queryWrapper = getConditionsByEntity(conditions);

        List<RoleMenuEntity> entities = roleMenuBatchMapper.list(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据数量
     *
     * @param conditions 查询条件
     * @return 查询到的数据数量
     */
    @Override
    public Long countByConditions(RoleMenuEntity conditions) {
        if (null == conditions) {
            return 0L;
        }

        QueryWrapper<RoleMenuEntity> queryWrapper = getConditionsByEntity(conditions);

        return roleMenuBatchMapper.count(queryWrapper);
    }

    /**
     * 通过角色ID查询按钮权限
     *
     * @param roleIds 角色ID列表
     * @return 角色按钮列表
     */
    @Override
    public List<RoleMenuEntity> listByRoleIds(List<Long> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return null;
        }

        LambdaQueryWrapper<RoleMenuEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(RoleMenuEntity::getRoleId, roleIds);
        return roleMenuBatchMapper.list(wrapper);
    }


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<RoleMenuEntity> getConditionsByEntity(RoleMenuEntity param) {
        QueryWrapper<RoleMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 角色id
                .eq(param.getRoleId() != null, RoleMenuEntity::getRoleId, param.getRoleId())
                // 菜单id
                .eq(param.getMenuId() != null, RoleMenuEntity::getMenuId, param.getMenuId())
        ;
        return queryWrapper;
    }

}
