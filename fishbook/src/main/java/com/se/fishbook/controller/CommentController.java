package com.se.fishbook.controller;

import com.se.fishbook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //direct to the page where you can start a new comment
    @RequestMapping("")
    public String comment(){
        return "";
    }

    //submit your new comment
    @RequestMapping("/submit_comment")
    public String submitComment(){
        return "";
    }
}
