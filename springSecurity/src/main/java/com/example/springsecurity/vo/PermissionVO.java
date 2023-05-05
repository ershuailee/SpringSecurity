package com.example.springsecurity.vo;

import com.example.springsecurity.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 菜单表
 *
 * @author 李二帅
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "Permission对象", description = "菜单表")
public class PermissionVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单名")
    private String permissionName;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    private Boolean visible;

    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    private Boolean status;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "节点类型: 0目录，1菜单，2按钮")
    private Integer nodeType;

    List<PermissionVO> child;
}
