package com.example.springsecurity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "用户注册DTO", description = "用户注册DTO")
public class UserRegisterDTO {

    @NotBlank(message = "ParamEmptyError")
    @ApiModelProperty("用户名称")
    private String username;

    @NotBlank(message = "ParamEmptyError")
    @ApiModelProperty("用户密码")
    private String password;
}
