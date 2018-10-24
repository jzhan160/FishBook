package com.se.fishbook.controller;

import com.se.fishbook.model.User;
import com.se.fishbook.model.UserKey;
import com.se.fishbook.service.UserService;
import com.se.fishbook.util.Constants;
import com.se.fishbook.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*
 * MainController class deals with login and registration activities
 * */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        System.out.println("in index page");
        System.out.println("user in session:"+request.getSession().getAttribute(Constants.CURRENT_USER));
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if (user!=null){ //get posts

        }
        return "index";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request) {
        //System.out.println("user in session:"+request.getSession().getAttribute(Constants.CURRENT_USER));
        return "home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody Result login(User user, HttpServletRequest request) {
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
        return result;
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

    /*
     * session management, find password.....
     * */
}
