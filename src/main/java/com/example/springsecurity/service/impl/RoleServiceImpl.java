package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.springsecurity.batchmapper.RoleBatchMapper;
import com.example.springsecurity.entity.user.RoleEntity;
import com.example.springsecurity.entity.user.UserRoleEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.service.RoleService;
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
 * 角色表 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleBatchMapper roleBatchMapper;

    @Resource
    private UserRoleService userRoleService;

    /**
     * 角色表新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleEntity param) {

        if (!roleBatchMapper.save(param)) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 角色表修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(RoleEntity param) {
        if (!roleBatchMapper.updateById(param)) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 角色表删除(单个条目)
     *
     * @param id 数据ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (!roleBatchMapper.removeById(id)) {
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
    public RoleEntity selectById(Long id) {
        if (null == id) {
            return null;
        }

        return roleBatchMapper.getById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<RoleEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<RoleEntity> entities = roleBatchMapper.listByIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<RoleEntity> listByConditions(RoleEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<RoleEntity> queryWrapper = getConditionsByEntity(conditions);

        List<RoleEntity> entities = roleBatchMapper.list(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据数量
     *
     * @param conditions 查询条件
     * @return 查询到的数据数量
     */
    @Override
    public int countByConditions(RoleEntity conditions) {
        if (null == conditions) {
            return 0;
        }

        QueryWrapper<RoleEntity> queryWrapper = getConditionsByEntity(conditions);

        return (int) roleBatchMapper.count(queryWrapper);
    }

    /**
     * 通过用户ID查询用户角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<RoleEntity> getRoleByUserId(Long userId) {

        List<UserRoleEntity> userRoleList = userRoleService.getRoleIdByUserId(userId);

        if (userRoleList == null || userRoleList.size() == 0) {
            return new ArrayList<>();
        }
        List<Long> roleIds = userRoleList.stream().map(UserRoleEntity::getRoleId).toList();

        return roleBatchMapper.listByIds(roleIds);
    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<RoleEntity> getConditionsByEntity(RoleEntity param) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, RoleEntity::getId, param.getId())
                // 角色名
                .eq(!StringUtils.isEmpty(param.getName()), RoleEntity::getName, param.getName())
                // 角色状态0-无效，1-有效
                .eq(param.getStatus() != null, RoleEntity::getStatus, param.getStatus())
                // 创建人主键
                .eq(param.getCreateId() != null, RoleEntity::getCreateId, param.getCreateId())
                // 创建时间
                .eq(param.getCreateTime() != null, RoleEntity::getCreateTime, param.getCreateTime())
                // 更新人主键
                .eq(param.getUpdateId() != null, RoleEntity::getUpdateId, param.getUpdateId())
                // 更新时间
                .eq(param.getUpdateTime() != null, RoleEntity::getUpdateTime, param.getUpdateTime())
                // 删除标记:0-正常,1-删除
                .eq(param.getDeleteFlag() != null, RoleEntity::getDeleteFlag, param.getDeleteFlag())
                // 备注
                .eq(!StringUtils.isEmpty(param.getRemark()), RoleEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}
