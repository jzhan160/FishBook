package com.se.fishbook.controller;

import com.se.fishbook.model.Notification;
import com.se.fishbook.model.User;
import com.se.fishbook.service.NotificationService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

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

    @RequestMapping("/addLikes")
    @ResponseBody
    public Integer addLikes(HttpServletRequest request){
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        Integer val = postService.addLikesCountByOne(postId);
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER)
        notificationService.newLikes(postId, user.getUserid());
        return val;
    }
}
