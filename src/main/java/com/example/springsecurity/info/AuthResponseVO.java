package com.example.springsecurity.info;

import lombok.Data;

/**
 * @author 李二帅
 * @since 2023/4/25 11:21
 */
@Data
public class AuthResponseVO {
    private String username;
    private String token;
}
