package com.atguigu.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController
 * Description:
 *
 * @Author: liuyang
 * @Create: 2023/7/26 - 4:15
 */
@RestController
public class TestController {

    @GetMapping("/test/hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/test/index")
    public String index() {
        return "hello index";
    }
}
