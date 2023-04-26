package com.example.springsecurity.controller;

import com.example.springsecurity.entity.common.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public abstract class BaseController {

    protected UserInfo getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof UserInfo) {
                return (UserInfo) authentication.getPrincipal();
            }
        }
        return null;
    }
}
