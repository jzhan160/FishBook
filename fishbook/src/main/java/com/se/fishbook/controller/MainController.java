package com.se.fishbook.controller;

import com.se.fishbook.model.*;
import com.se.fishbook.service.CommentService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.service.RelationService;
import com.se.fishbook.service.UserService;
import com.se.fishbook.test.Location;
import com.se.fishbook.util.Constants;
import com.se.fishbook.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/*
 * MainController class deals with login and registration activities
 * */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    //Personal main page
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
         System.out.println("in index page");
        System.out.println("user in session:" + request.getSession().getAttribute(Constants.CURRENT_USER));
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        if (user != null) {
            System.out.println("==================Loading the post...===============");
            //show all posts of the current user and his followees
            List<Integer> usersToShowPosts = relationService.allFollowees(user);
            usersToShowPosts.add(user.getUserid());
            List<Post> posts = postService.postsByUserIds(usersToShowPosts);


            List<PostDisplay> postDisplays = new ArrayList<>();
            for(Post post : posts){
                PostDisplay pd = new PostDisplay();
                UserKey key = new UserKey();
                key.setUserid(post.getAuthorid());
                User author  = userService.selectById(key);
                pd.setPost(post);
                pd.setUser(author);
                pd.setCommentCount(commentService.commentCountByPostId(post.getPostid()));
                postDisplays.add(pd);
            }
            System.out.println("==================Total Num:"+ posts.size() +"===============");
            //Set the Model and View
            mv.setViewName("/index");
            mv.addObject("posts", postDisplays);
            return mv;
        }
        mv.setViewName("/index");
        return mv;
    }

    @RequestMapping("home")
    public @ResponseBody
    Result home() {
        System.out.println("home");
        Result result = new Result();
        result.setCode(Constants.SUCCESS);
        return result;
    }

    //login method
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody Result login(User user, Location location,HttpServletRequest request) {
        System.out.println("==================in the login method===============");
        System.out.println("==================user is " + user);
        UserKey key = new UserKey();
        key.setEmail(user.getEmail());
        User user_checked = userService.selectByEmail(key);
        System.out.println("user_checked is " + user_checked);

        Result result = new Result();
        if (user_checked != null) {
            if (user_checked.getPassword().equals(user.getPassword())) {
                request.getSession().setAttribute(Constants.CURRENT_USER, user_checked);
                request.getSession().setAttribute(Constants.CURRENT_LOCATION, location);

                System.out.println(location);
                //System.out.println("user is "+user);
                // System.out.println("user_checked is "+user_checked);

                result.setCode(Constants.SUCCESS);
            } else {
                result.setCode(Constants.ERROR);
                result.setMsg("Incorrect Password!");
            }
        } else {
            result.setCode(Constants.ERROR);
            result.setMsg("Account does not exist!");
        }
        // System.out.println(result.getCode());
        // System.out.println(result.getMsg());
        //return "index";
        return  result;
    }

    //register method
    @RequestMapping(value = "/sign_up",method = RequestMethod.POST)
    public String sign_up(User user,HttpServletRequest request) {
        user.setGender("Unknown");
        user.setCreatetime(new Date());
        user.setIsadmin(0);
        user.setImagepath("");
        int id = userService.addUser(user);
        System.out.println(id);
        request.getSession().setAttribute(Constants.CURRENT_USER, user);
        return "/index";
    }

    //logout and clear sessions
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.CURRENT_USER, null);
        return "/index";
    }


    @RequestMapping(value = "test")
    public  String test() {
        return "test/testAjax";
    }

    @RequestMapping(value = "testResult", method = RequestMethod.POST)
    public @ResponseBody String testResult() {
        return "test";
    }
    /*
     * session management, find password.....
     * */
}
