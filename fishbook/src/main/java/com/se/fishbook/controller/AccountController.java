package com.se.fishbook.controller;
import com.se.fishbook.service.NotificationService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* AccountController class handles requests about user accounts
* */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PostService postService;

    //direct to the profile page
    @RequestMapping("/profile")
    public String profile(){
        return "account/profile";
    }

    //direct to the page where you can edit the password
    @RequestMapping("/edit_password")
    public String editPassword(){
        return "";
    }

    //submit the new password
    @RequestMapping("/update_password")
    public String updatePassword(){
        return "";
    }

    //direct to the page where you can upload the personal image
    @RequestMapping("/edit_image")
    public String editImage(){
        return "";
    }

    //submit the new image
    @RequestMapping("/update_image")
    public String updateImage(){
        return "";
    }

    @RequestMapping("/notification")
    public String notification(){
        return "";
    }

    //
    @RequestMapping("/setting")
    public String previousPosts(){
        return "";
    }


}
