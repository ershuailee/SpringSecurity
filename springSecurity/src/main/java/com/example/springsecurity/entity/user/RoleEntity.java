package com.example.springsecurity.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.springsecurity.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色表
 *
 * @author 李二帅
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("role")
@ApiModel(value = "RoleEntity对象", description = "角色表")
public class RoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "角色状态0-无效，1-有效")
    private Boolean status;

}
