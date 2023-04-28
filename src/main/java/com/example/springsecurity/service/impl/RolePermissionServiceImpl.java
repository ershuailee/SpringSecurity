package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.batchmapper.RolePermissionBatchMapper;
import com.example.springsecurity.dto.RolePermissionDTO;
import com.example.springsecurity.entity.user.RolePermissionEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.service.RolePermissionService;
import com.example.springsecurity.vo.RolePermissionVO;
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
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private RolePermissionBatchMapper rolePermissionBatchMapper;


    /**
     * 角色新增权限
     *
     * @param dto 请求数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRolePermission(RolePermissionDTO dto) {

    }

    /**
     * 根据角色ID查询角色权限
     *
     * @param id 角色ID
     * @return 权限数据
     */
    @Override
    public List<RolePermissionVO> getRolePermission(Long id) {
        return null;
    }

    /**
     * 通过角色ID查询按钮权限
     *
     * @param roleIds 角色ID列表
     * @return 角色按钮列表
     */
    @Override
    public List<RolePermissionEntity> listByRoleIds(List<Long> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return null;
        }

        LambdaQueryWrapper<RolePermissionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(RolePermissionEntity::getRoleId, roleIds);
        return rolePermissionBatchMapper.list(wrapper);
    }


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<RolePermissionEntity> getConditionsByEntity(RolePermissionEntity param) {
        QueryWrapper<RolePermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 角色id
                .eq(param.getRoleId() != null, RolePermissionEntity::getRoleId, param.getRoleId())
                // 菜单id
                .eq(param.getPermissionId() != null, RolePermissionEntity::getPermissionId, param.getPermissionId())
        ;
        return queryWrapper;
    }

}
