package com.example.springsecurity.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Api(tags = "test")
public class TestController {

    @GetMapping("/getTest")
    @ApiOperation(value = "getTest", notes = "getTest", httpMethod = "GET")
    public String getTest() {
        return "getTest";
    }

    @PostMapping("/postTest")
    @ApiOperation(value = "postTest", notes = "postTest", httpMethod = "POST")
    public String listAllPermissions() {
        return "postTest";
    }

    @GetMapping("/getTest1")
    @ApiOperation(value = "getTest1", notes = "getTest1", httpMethod = "GET")
    public String getTest1(@RequestParam String param) {
        return param;
    }

    @PostMapping("/postTest1")
    @ApiOperation(value = "postTest1", notes = "postTest1", httpMethod = "POST")
    public String postTest1(@RequestBody String param) {
        return param;
    }

}
