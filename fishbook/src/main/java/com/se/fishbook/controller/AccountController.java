package com.se.fishbook.controller;
import com.se.fishbook.model.Post;
import com.se.fishbook.model.User;
import com.se.fishbook.service.NotificationService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.service.UserService;
import com.se.fishbook.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ModelAndView profile(HttpServletRequest request){
        System.out.println("in index page");
        System.out.println("user in session:"+request.getSession().getAttribute(Constants.CURRENT_USER));
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        ModelAndView mv = new ModelAndView("account/profile");
        if (user!=null){
            System.out.println("==================Loading the post...===============");
            List<Post> posts = postService.postByUserId(user.getUserid());
            System.out.println("==================Total Num:"+ posts.size() +"===============");
            System.out.println(posts.get(0).getContent());
            mv.addObject("posts",posts);
        }
        return mv;
    }

    @RequestMapping("/deletePost/{postid}")
    private String deletePost(@PathVariable String postid,HttpServletRequest request){
        System.out.println("Delete.....");
        System.out.println(postid);
        /*here we need to implement the deletion from the post table*/
        return "redirect:/account/profile";
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

}
