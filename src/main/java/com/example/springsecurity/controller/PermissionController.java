package com.example.springsecurity.controller;

import com.example.springsecurity.dto.PermissionDTO;
import com.example.springsecurity.dto.RolePermissionDTO;
import com.example.springsecurity.service.PermissionService;
import com.example.springsecurity.service.RolePermissionService;
import com.example.springsecurity.vo.PermissionVO;
import com.example.springsecurity.vo.RolePermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限控制类
 *
 * @author 李二帅
 * @since 2023/4/28 16:36
 */
@RestController
@RequestMapping("/permission")
@Api(tags = "权限控制类")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @Resource
    private RolePermissionService rolePermissionService;

    @PostMapping("/addPermission")
    @ApiOperation(value = "新增权限", notes = "新增权限", httpMethod = "POST")
    public void addPermission(@RequestBody PermissionDTO dto) {
        permissionService.addPermission(dto);
    }

    @GetMapping("/getPermission")
    @ApiOperation(value = "获取权限树", notes = "获取权限树", httpMethod = "GET")
    public List<PermissionVO> getPermission() {
        return permissionService.getPermission();
    }

    @PostMapping("/addRolePermission")
    @ApiOperation(value = "角色新增权限", notes = "角色新增权限", httpMethod = "POST")
    public void addRolePermission(@RequestBody RolePermissionDTO dto) {
        rolePermissionService.addRolePermission(dto);
    }

    @GetMapping("/getRolePermission")
    @ApiOperation(value = "根据角色ID查询角色权限", notes = "根据角色ID查询角色权限", httpMethod = "GET")
    public List<RolePermissionVO> getRolePermission(Long id) {
        return rolePermissionService.getRolePermission(id);
    }


}
