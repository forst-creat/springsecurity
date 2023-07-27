package com.atguigu.springsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController
 * Description:
 *
 * @Author: liuyang
 * @Create: 2023/7/26 - 4:15
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    @GetMapping("/update")
//    @Secured({"ROLE_manager"})  //只有有内部的角色才能访问该方法
    @PreAuthorize("hasAnyAuthority('admin')")
    public String update() {
        return "hello update";
    }
}
