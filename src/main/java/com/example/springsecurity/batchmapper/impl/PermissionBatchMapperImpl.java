package com.example.springsecurity.batchmapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.batchmapper.PermissionBatchMapper;
import com.example.springsecurity.entity.user.PermissionEntity;
import com.example.springsecurity.mapper.PermissionMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 菜单表 存储实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Component
public class PermissionBatchMapperImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionBatchMapper {

}
