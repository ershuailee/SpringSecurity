package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.springsecurity.batchmapper.PermissionBatchMapper;
import com.example.springsecurity.dto.PermissionDTO;
import com.example.springsecurity.entity.user.PermissionEntity;
import com.example.springsecurity.entity.user.RoleEntity;
import com.example.springsecurity.entity.user.RolePermissionEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.service.PermissionService;
import com.example.springsecurity.service.RolePermissionService;
import com.example.springsecurity.service.RoleService;
import com.example.springsecurity.util.ConvertUtils;
import com.example.springsecurity.vo.PermissionTreeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Resource
    private RoleService roleService;

    /**
     * 新增权限数据
     *
     * @param dto 请求数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertPermission(PermissionDTO dto) {
        PermissionEntity permission = ConvertUtils.convert(dto, PermissionEntity.class);

        if (!permissionBatchMapper.save(permission)) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 获取全部权限数据
     *
     * @return 权限树
     */
    @Override
    public List<PermissionTreeVO> listAllPermissions() {
        List<PermissionEntity> list = permissionBatchMapper.list();
        return encapsulationPermissionVo(list, 0L);
    }

    /**
     * 获取用户权限列表
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public List<PermissionTreeVO> listPermissionsByUserId(Long userId) {

        // 根据用户ID查询角色数据
        if (userId == null) {
            return new ArrayList<>();
        }
        List<RoleEntity> roleList = roleService.getRoleByUserId(userId);

        if (roleList == null) {
            return new ArrayList<>();
        }
        List<Long> roleIds = roleList.stream().map(RoleEntity::getId).collect(Collectors.toList());

        // 根据角色ID查询权限ID
        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.listByRoleIds(roleIds);
        List<Long> permissionIds = rolePermissionEntityList.stream()
                .map(RolePermissionEntity::getPermissionId).collect(Collectors.toList());

        // 查询权限数据
        List<PermissionEntity> list = permissionBatchMapper.listByIds(permissionIds);
        // 0L：表示根节点的父ID
        return encapsulationPermissionVo(list, 0L);
    }

    /**
     * 封装权限视图
     *
     * @param allPermission 所有权限列表
     * @param parentId      父节点ID
     * @return 权限树
     */
    private List<PermissionTreeVO> encapsulationPermissionVo(List<PermissionEntity> allPermission, Long parentId) {
        List<PermissionTreeVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(allPermission)) {
            for (PermissionEntity permission : allPermission) {
                if (Objects.equals(parentId, permission.getParentId())) {
                    PermissionTreeVO permissionTreeVO = new PermissionTreeVO();
                    BeanUtils.copyProperties(permission, permissionTreeVO);
                    //递归查询子菜单，并封装信息
                    List<PermissionTreeVO> childList = encapsulationPermissionVo(allPermission, permission.getId());
                    if (!CollectionUtils.isEmpty(childList)) {
                        permissionTreeVO.setChild(childList);
                    }
                    resultList.add(permissionTreeVO);
                }
            }
        }
        return resultList;
    }
}
