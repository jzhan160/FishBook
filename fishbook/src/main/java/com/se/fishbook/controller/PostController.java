package com.se.fishbook.controller;

import com.se.fishbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    //direct to the page where you can start a new post
    @RequestMapping("")
    public String post(){
        return "";
    }

    //submit your new post
    @RequestMapping("/submit_post")
    public String submitPost(){

        return "";
    }
}
