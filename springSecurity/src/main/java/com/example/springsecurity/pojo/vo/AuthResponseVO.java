package com.example.springsecurity.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 授权响应 VO
 * @author 李二帅
 * @since 2023/4/25 11:21
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "授权响应 VO", description = "授权响应 VO")
public class AuthResponseVO {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户token")
    private String token;

}
