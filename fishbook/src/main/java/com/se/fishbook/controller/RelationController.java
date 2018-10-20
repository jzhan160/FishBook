package com.se.fishbook.controller;

import com.se.fishbook.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    //click the follow button to follow a user
    @RequestMapping("/follow")
    public String follow(){
        return "";
    }

    //click the follow button to unfollow a user
    @RequestMapping("/unfollow")
    public String unfollow(){
        return "";
    }

    //look through all your followers
    @RequestMapping("/followers")
    public String followers(){
        return "";
    }

    //look through all your followee
    @RequestMapping("/followee")
    public String followee(){
        return "";
    }
}
