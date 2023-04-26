package com.example.springsecurity.batchmapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.batchmapper.UserRoleBatchMapper;
import com.example.springsecurity.entity.user.UserRoleEntity;
import com.example.springsecurity.mapper.UserRoleMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户角色关联表 存储实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Component
public class UserRoleBatchMapperImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleBatchMapper {

}
