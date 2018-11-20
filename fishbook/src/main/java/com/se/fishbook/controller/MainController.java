package com.se.fishbook.controller;

import com.se.fishbook.model.*;
import com.se.fishbook.service.CommentService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.service.RelationService;
import com.se.fishbook.service.UserService;
import com.se.fishbook.test.JsonResult;
import com.se.fishbook.util.Constants;
import com.se.fishbook.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<Post> posts = postService.postsByUserIds(relationService.allFollowees(user));

            System.out.println("==================Total Num:" + posts.size() + "===============");
            Map<Integer, List<Comment>> comments = new HashMap<>();
            int i = 0;
            for (Post post : posts) {
                comments.put(i, commentService.selectCommentsByPostId(post.getPostid()));
                i++;
            }
            List<PostDisplay> postDisplays = new ArrayList<>();
            for(Post post : posts){
                PostDisplay pd = new PostDisplay();
                UserKey key = new UserKey();
                key.setUserid(post.getAuthorid());
                User author  = userService.selectById(key);
                pd.setPost(post);
                pd.setUser(author);
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

   /* @RequestMapping(value = "login",method = RequestMethod.POST)
    public @ResponseBody  Result login(User user, HttpServletRequest request) {
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
    }*/

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request) {
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
        return "redirect:index";
    }

    @RequestMapping("/sign_up")
    public String sign_up() {
        return "";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.CURRENT_USER, null);
        return "/index";
    }

    /*
     * session management, find password.....
     * */

    @RequestMapping("testJson")
    public String testJson(){
        return "test/testJson";
    }


    //get post location
    //just for test
    @ResponseBody
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public List<JsonResult> getData(JsonResult jsonResult) {
        System.out.println("==============getData");
        System.out.println(jsonResult);
        List<JsonResult> results = new ArrayList<>();
        try {
            //mock data
            //we need locations from database
            JsonResult jsonResult1 = new JsonResult();
            jsonResult1.setLat(42.9974823);
            jsonResult1.setLng(-76.1259995);
            results.add( jsonResult1);
            JsonResult jsonResult2 = new JsonResult();
            jsonResult2.setLat(42.9972800);
            jsonResult2.setLng(-76.1299064);
            results.add( jsonResult2);

        } catch (Exception e) {
            System.out.println("error");
        }
        return results;
    }
}
