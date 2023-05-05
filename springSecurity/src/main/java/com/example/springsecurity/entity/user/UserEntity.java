package com.example.springsecurity.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.springsecurity.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户表
 *
 * @author 李二帅
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "UserEntity对象", description = "用户表")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "状态0-无效，1-有效")
    private Boolean status;


}
