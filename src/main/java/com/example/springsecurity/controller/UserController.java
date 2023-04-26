package com.example.springsecurity.controller;

import com.example.springsecurity.dto.UserRegisterDTO;
import com.example.springsecurity.entity.common.UserInfo;
import com.example.springsecurity.dto.AuthRequestDTO;
import com.example.springsecurity.vo.AuthResponseVO;
import com.example.springsecurity.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2023-03-27
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    public void register(@RequestBody UserRegisterDTO user) {
        userService.register(user);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    public AuthResponseVO login(@RequestBody AuthRequestDTO dto) {
        return userService.login(dto);
    }

    @GetMapping("/getPermissions")
    @ApiOperation(value = "获取用户权限列表", notes = "获取用户权限列表", httpMethod = "GET")
    public UserInfo getPermissions() {
        return getUserDetails();
    }

}
