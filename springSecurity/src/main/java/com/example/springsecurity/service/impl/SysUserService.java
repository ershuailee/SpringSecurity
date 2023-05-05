package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.springsecurity.batchmapper.UserBatchMapper;
import com.example.springsecurity.entity.user.UserEntity;
import com.example.springsecurity.entity.user.UserInfo;
import com.example.springsecurity.util.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SysUserService
 *
 * @author 李二帅
 * @since 2023/4/25 11:04
 */
@Slf4j
@Service
public class SysUserService implements UserDetailsService {

    @Resource
    private UserBatchMapper userBatchMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(username), UserEntity::getUsername, username);
        UserEntity userEntity = userBatchMapper.getOne(wrapper);

        UserInfo userInfo = ConvertUtils.convert(userEntity, UserInfo.class);
        userInfo.setUserId(userEntity.getId());
        userInfo.setEnabled(userEntity.getStatus());

        return userInfo;
    }

}