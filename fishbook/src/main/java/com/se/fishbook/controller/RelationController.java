package com.se.fishbook.controller;

import com.se.fishbook.model.User;
import com.se.fishbook.service.RelationService;
import com.se.fishbook.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /*get all followers */
    @RequestMapping(value="followers",method= RequestMethod.POST)
    public ModelAndView followers(HttpServletRequest request){
        ModelAndView mav=new ModelAndView();
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null) {
            List<Integer> followers = relationService.allFollowers(user);
            mav.addObject("followers", followers);
            mav.setViewName("showAllFollowers");
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
            mav.setViewName("showAllFollowees");
        }
        return mav;
    }
    /*下边这块跟我上头的重复了*/
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
    /*重复*/
}
