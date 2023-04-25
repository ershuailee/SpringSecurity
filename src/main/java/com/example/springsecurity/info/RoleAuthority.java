package com.example.springsecurity.info;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author 李二帅
 * @since 2023/4/25 11:05
 */
@Data
public class RoleAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {

        return null;
    }
}
