package com.se.fishbook.controller;

import com.se.fishbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * MainController class deals with login and registration activities
 * */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "";
    }

    @RequestMapping("/sign_up")
    public String sign_up() {
        return "";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "";
    }

    /*
     * session management, find password.....
     * */
}
