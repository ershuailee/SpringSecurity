package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.dto.PermissionDTO;
import com.example.springsecurity.service.PermissionService;
import com.example.springsecurity.pojo.vo.PermissionTreeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class PermissionController extends BaseController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("/insertPermission")
    @ApiOperation(value = "新增权限数据", notes = "新增权限数据", httpMethod = "POST")
    public void insertPermission(@RequestBody PermissionDTO dto) {
        dto.setCreateId(getUserDetails().getUserId());
        dto.setCreateTime(LocalDateTime.now());
        permissionService.insertPermission(dto);
    }

    @GetMapping("/getAllPermissions")
    @ApiOperation(value = "获取所有权限列表", notes = "获取所有权限列表", httpMethod = "GET")
    public List<PermissionTreeVO> listAllPermissions() {
        return permissionService.listAllPermissions();
    }

    @GetMapping("/listPermissionsByUserId")
    @ApiOperation(value = "根据用户获取用户权限列表", notes = "根据用户获取用户权限列表", httpMethod = "GET")
    public List<PermissionTreeVO> listPermissionsByUserId() {
        return permissionService.listPermissionsByUserId(getUserDetails().getUserId());
    }

}
