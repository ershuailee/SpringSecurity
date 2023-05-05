package com.example.springsecurity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 授权请求 DTO
 *
 * @author 李二帅
 * @since 2023/4/25 11:22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "授权请求 DTO", description = "授权请求 DTO")
public class AuthRequestDTO {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

}
