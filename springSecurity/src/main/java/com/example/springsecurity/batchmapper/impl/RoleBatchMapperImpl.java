package com.example.springsecurity.batchmapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.batchmapper.RoleBatchMapper;
import com.example.springsecurity.pojo.entity.RoleEntity;
import com.example.springsecurity.mapper.RoleMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色表 存储实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Component
public class RoleBatchMapperImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleBatchMapper {

}
