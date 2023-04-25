package com.example.springsecurity.info;

import lombok.Data;

/**
 * @author 李二帅
 * @since 2023/4/25 11:22
 */
@Data
public class AuthRequestVO {
    private String username;
    private String password;
}
