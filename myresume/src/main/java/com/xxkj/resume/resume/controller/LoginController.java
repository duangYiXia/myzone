package com.xxkj.resume.resume.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zjx
 * @create 2020/3/20 23:44
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public void login(User user) {

    }
}
