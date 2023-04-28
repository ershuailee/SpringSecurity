package com.example.springsecurity.batchmapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.batchmapper.RolePermissionBatchMapper;
import com.example.springsecurity.entity.user.RolePermissionEntity;
import com.example.springsecurity.mapper.RolePermissionMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色菜单关联表 存储实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Component
public class RolePermissionBatchMapperImpl extends ServiceImpl<RolePermissionMapper, RolePermissionEntity> implements RolePermissionBatchMapper {

}
