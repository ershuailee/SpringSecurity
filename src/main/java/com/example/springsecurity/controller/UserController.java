package com.example.springsecurity.controller;

import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.info.AuthRequestVO;
import com.example.springsecurity.info.AuthResponseVO;
import com.example.springsecurity.service.JWTService;
import com.example.springsecurity.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private JWTService jwtService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    public AuthResponseVO login(@RequestBody AuthRequestVO vo) {
        try {
            return userService.login(vo);
        } catch (BusinessException e) {
            throw new BusinessException(BusinessErrorCodes.INVALID_TOKEN);
        }
    }

    @GetMapping("/test")
    @ApiOperation(value = "test", notes = "test", httpMethod = "GET")
    public String test(HttpServletRequest request) {
        String token = jwtService.getToken(request);
        String s = jwtService.validateToken(token);
        System.out.println(s);
        return s;
    }


    @GetMapping("/test1")
    @ApiOperation(value = "test1", notes = "test1", httpMethod = "GET")
    public Integer test1() {
        return 1;
    }

    @GetMapping("/test2")
    @ApiOperation(value = "test2", notes = "test2", httpMethod = "GET")
    public List<String> test2() {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("111");
        return objects;
    }
}
