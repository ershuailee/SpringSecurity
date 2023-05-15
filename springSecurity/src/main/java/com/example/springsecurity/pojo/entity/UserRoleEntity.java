package com.example.springsecurity.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户角色关联表
 *
 * @author 李二帅
 */
@Data
@Accessors(chain = true)
@TableName("user_role")
@ApiModel(value = "UserRoleEntity对象", description = "用户角色关联表")
public class UserRoleEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.NONE)
    private Long userId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;


}
