package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.springsecurity.batchmapper.MenuBatchMapper;
import com.example.springsecurity.entity.user.MenuEntity;
import com.example.springsecurity.entity.user.RoleMenuEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.service.MenuService;
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
 * 菜单表 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuBatchMapper menuBatchMapper;

    @Resource
    private RoleMenuService roleMenuService;


    /**
     * 菜单表新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MenuEntity param) {

        if (!menuBatchMapper.save(param)) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 菜单表修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(MenuEntity param) {
        if (!menuBatchMapper.updateById(param)) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 菜单表删除(单个条目)
     *
     * @param id 数据ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (!menuBatchMapper.removeById(id)) {
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
    public MenuEntity selectById(Long id) {
        if (null == id) {
            return null;
        }

        return menuBatchMapper.getById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<MenuEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<MenuEntity> entities = menuBatchMapper.listByIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<MenuEntity> listByConditions(MenuEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<MenuEntity> queryWrapper = getConditionsByEntity(conditions);

        List<MenuEntity> entities = menuBatchMapper.list(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据数量
     *
     * @param conditions 查询条件
     * @return 查询到的数据数量
     */
    @Override
    public Long countByConditions(MenuEntity conditions) {
        if (null == conditions) {
            return 0L;
        }

        QueryWrapper<MenuEntity> queryWrapper = getConditionsByEntity(conditions);

        return menuBatchMapper.count(queryWrapper);
    }

    /**
     * 通过角色ID列表查询角色数据
     *
     * @param roleIds 角色ID列表
     * @return 角色按钮权限
     */
    @Override
    public List<MenuEntity> getUserMenuByRoleId(List<Long> roleIds) {
        // 根据角色ID查询按钮ID
        List<RoleMenuEntity> roleMenuEntityList = roleMenuService.listByRoleIds(roleIds);

        // 根据按钮ID查询按钮数据
        List<Long> menuIds = roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).toList();
        return menuBatchMapper.listByIds(menuIds);
    }


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<MenuEntity> getConditionsByEntity(MenuEntity param) {
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, MenuEntity::getId, param.getId())
                // 菜单名
                .eq(!StringUtils.isEmpty(param.getMenuName()), MenuEntity::getMenuName, param.getMenuName())
                // 路由地址
                .eq(!StringUtils.isEmpty(param.getPath()), MenuEntity::getPath, param.getPath())
                // 组件路径
                .eq(!StringUtils.isEmpty(param.getComponent()), MenuEntity::getComponent, param.getComponent())
                // 菜单状态（0显示 1隐藏）
                .eq(param.getVisible() != null, MenuEntity::getVisible, param.getVisible())
                // 菜单状态（0正常 1停用）
                .eq(param.getStatus() != null, MenuEntity::getStatus, param.getStatus())
                // 权限标识
                .eq(!StringUtils.isEmpty(param.getPerms()), MenuEntity::getPerms, param.getPerms())
                // 菜单图标
                .eq(!StringUtils.isEmpty(param.getIcon()), MenuEntity::getIcon, param.getIcon())
                // 创建人主键
                .eq(param.getCreateId() != null, MenuEntity::getCreateId, param.getCreateId())
                // 创建时间
                .eq(param.getCreateTime() != null, MenuEntity::getCreateTime, param.getCreateTime())
                // 更新人主键
                .eq(param.getUpdateId() != null, MenuEntity::getUpdateId, param.getUpdateId())
                // 更新时间
                .eq(param.getUpdateTime() != null, MenuEntity::getUpdateTime, param.getUpdateTime())
                // 删除标记:0-正常,1-删除
                .eq(param.getDeleteFlag() != null, MenuEntity::getDeleteFlag, param.getDeleteFlag())
                // 备注
                .eq(!StringUtils.isEmpty(param.getRemark()), MenuEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}
