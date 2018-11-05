package com.se.fishbook.controller;

import com.se.fishbook.model.Post;
import com.se.fishbook.model.User;
import com.se.fishbook.model.UserKey;
import com.se.fishbook.service.PostService;
import com.se.fishbook.service.RelationService;
import com.se.fishbook.service.UserService;
import com.se.fishbook.util.Constants;
import com.se.fishbook.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    //Personal main page
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        System.out.println("in index page");
        System.out.println("user in session:"+request.getSession().getAttribute(Constants.CURRENT_USER));
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if (user!=null){
            System.out.println("==================Loading the post...===============");
            List<Post> posts = postService.postsByUserIds(relationService.allFollowees(user));
            System.out.println("==================Total Num:"+ posts.size() +"===============");
            //TODO: model and view???
        }
        return "index";
    }

    @RequestMapping("home")
    public @ResponseBody Result  home() {
        System.out.println("home");
        Result result = new Result();
        result.setCode(Constants.SUCCESS);
        return result;
    }



    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request) {
        System.out.println("==================in the login method");
        System.out.println("==================user is"+user);
        UserKey key = new UserKey();
        key.setEmail(user.getEmail());
        User user_checked = userService.selectByEmail(key);
        System.out.println("user_checked is "+user_checked);

        Result result = new Result();
        if(user_checked!=null){
            if(user_checked.getPassword().equals(user.getPassword())){
                request.getSession().setAttribute(Constants.CURRENT_USER, user_checked);
                //System.out.println("user is "+user);
                // System.out.println("user_checked is "+user_checked);

                result.setCode(Constants.SUCCESS);
            }else{
                result.setCode(Constants.ERROR);
                result.setMsg("Incorrect Password!");
            }
        }else{
            result.setCode(Constants.ERROR);
            result.setMsg("Account does not exist!");
        }
        // System.out.println(result.getCode());
        // System.out.println(result.getMsg());
        return "index";
    }


    @RequestMapping("/sign_up")
    public String sign_up() {
        return "";
    }

    @RequestMapping(value="logout",method=RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute(Constants.CURRENT_USER, null);
        return "/index";
    }
    /*get all followers */
    @RequestMapping(value="followers",method=RequestMethod.POST)
    public ModelAndView followers(HttpServletRequest request){
        ModelAndView mav=new ModelAndView();
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null) {
            List<Integer> followers = relationService.allFollowers(user);
            mav.addObject("followers", followers);
            mav.setViewName("showallfollowers");
        }
        return mav;
    }
    /*get all followees*/
    @RequestMapping(value="followees",method=RequestMethod.POST)
    public ModelAndView followees(HttpServletRequest request){
        ModelAndView mav=new ModelAndView();
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null) {
            List<Integer> followees = relationService.allFollowees(user);
            mav.addObject("followees", followees);
            mav.setViewName("showallfollowees");
        }
        return mav;
    }
    /*
     * session management, find password.....
     * */
}
