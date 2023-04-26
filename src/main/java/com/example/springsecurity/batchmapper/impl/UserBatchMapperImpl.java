package com.example.springsecurity.batchmapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.batchmapper.UserBatchMapper;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户表 存储实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-01
 */
@Component
public class UserBatchMapperImpl extends ServiceImpl<UserMapper, UserEntity> implements UserBatchMapper {

}
