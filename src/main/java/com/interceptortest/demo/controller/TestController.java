package com.interceptortest.demo.controller;

import com.interceptortest.demo.component.Anno;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("1")
    @Anno
    public String test() {
        return "test";
    }

    @GetMapping("2")
    public String test2() {
        return "test";
    }
}
