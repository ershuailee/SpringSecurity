package com.example.springsecurity.service.impl;

import com.example.springsecurity.info.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * SysUserService
 *
 * @author 李二帅
 * @since 2023/4/25 11:04
 */
@Slf4j
@Service
public class SysUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(username);

        return userInfo;
    }

}