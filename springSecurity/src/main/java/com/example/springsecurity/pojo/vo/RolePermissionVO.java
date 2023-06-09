package com.example.springsecurity.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色菜单关联表
 *
 * @author 李二帅
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "RolePermissionVO", description = "角色菜单关联表")
public class RolePermissionVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @TableId(value = "role_id", type = IdType.NONE)
    private Long roleId;

    @ApiModelProperty(value = "菜单id")
    private Long permissionId;

}
