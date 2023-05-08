package com.example.springsecurity.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "test")
public class TestController {

    @GetMapping("/test")
    @ApiOperation(value = "test", notes = "test", httpMethod = "GET")
    public Boolean listAllPermissions() {
        return true;
    }

}
