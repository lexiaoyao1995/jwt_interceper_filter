package com.interceptortest.demo.jwt.component.controller;

import com.interceptortest.demo.jwt.component.Audience;
import com.interceptortest.demo.jwt.component.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("JWT")
public class JWTController {

    @Autowired
    private Audience audience;

    @GetMapping("get")
    public String test() {
        return JwtTokenUtils.createJWT("1", "1", "11", "132", audience);
    }

    @PostMapping("parse")
    public String test1(HttpServletRequest httpRequest) {
        String author = httpRequest.getHeader("author");
        Claims claims = JwtTokenUtils.parseJWT(author, audience.getBase64Secret());
        Object code = claims.get("code");
        return author;
    }

}
