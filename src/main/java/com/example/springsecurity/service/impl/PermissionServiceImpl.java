package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.springsecurity.batchmapper.PermissionBatchMapper;
import com.example.springsecurity.dto.PermissionDTO;
import com.example.springsecurity.entity.user.PermissionEntity;
import com.example.springsecurity.entity.user.RolePermissionEntity;
import com.example.springsecurity.service.PermissionService;
import com.example.springsecurity.service.RolePermissionService;
import com.example.springsecurity.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionBatchMapper permissionBatchMapper;

    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 新增权限
     *
     * @param dto 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPermission(PermissionDTO dto) {



    }

    /**
     * 获取权限树
     *
     * @return 权限树
     */
    @Override
    public List<PermissionVO> getPermission() {
        return null;
    }

    /**
     * 通过角色ID列表查询角色数据
     *
     * @param roleIds 角色ID列表
     * @return 角色按钮权限
     */
    @Override
    public List<PermissionEntity> getUserMenuByRoleId(List<Long> roleIds) {
        // 根据角色ID查询按钮ID
        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.listByRoleIds(roleIds);

        // 根据按钮ID查询按钮数据
        List<Long> menuIds = rolePermissionEntityList.stream().map(RolePermissionEntity::getPermissionId).toList();
        return permissionBatchMapper.listByIds(menuIds);
    }


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<PermissionEntity> getConditionsByEntity(PermissionEntity param) {
        QueryWrapper<PermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, PermissionEntity::getId, param.getId())
                // 菜单名
                .eq(!StringUtils.isEmpty(param.getPermissionName()), PermissionEntity::getPermissionName,
                        param.getPermissionName())
                // 路由地址
                .eq(!StringUtils.isEmpty(param.getPath()), PermissionEntity::getPath, param.getPath())
                // 组件路径
                .eq(!StringUtils.isEmpty(param.getComponent()), PermissionEntity::getComponent, param.getComponent())
                // 菜单状态（0显示 1隐藏）
                .eq(param.getVisible() != null, PermissionEntity::getVisible, param.getVisible())
                // 菜单状态（0正常 1停用）
                .eq(param.getStatus() != null, PermissionEntity::getStatus, param.getStatus())
                // 权限标识
                .eq(!StringUtils.isEmpty(param.getPerms()), PermissionEntity::getPerms, param.getPerms())
                // 菜单图标
                .eq(!StringUtils.isEmpty(param.getIcon()), PermissionEntity::getIcon, param.getIcon())
                // 创建人主键
                .eq(param.getCreateId() != null, PermissionEntity::getCreateId, param.getCreateId())
                // 创建时间
                .eq(param.getCreateTime() != null, PermissionEntity::getCreateTime, param.getCreateTime())
                // 更新人主键
                .eq(param.getUpdateId() != null, PermissionEntity::getUpdateId, param.getUpdateId())
                // 更新时间
                .eq(param.getUpdateTime() != null, PermissionEntity::getUpdateTime, param.getUpdateTime())
                // 删除标记:0-正常,1-删除
                .eq(param.getDeleteFlag() != null, PermissionEntity::getDeleteFlag, param.getDeleteFlag())
                // 备注
                .eq(!StringUtils.isEmpty(param.getRemark()), PermissionEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}
