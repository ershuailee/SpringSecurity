package com.example.springsecurity.batchmapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.batchmapper.RoleMenuBatchMapper;
import com.example.springsecurity.entity.user.RoleMenuEntity;
import com.example.springsecurity.mapper.RoleMenuMapper;
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
public class RoleMenuBatchMapperImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuBatchMapper {

}
