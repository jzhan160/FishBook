package com.se.fishbook.controller;

import com.se.fishbook.model.User;
import com.se.fishbook.model.UserKey;
import com.se.fishbook.service.RelationService;
import com.se.fishbook.service.UserService;
import com.se.fishbook.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;
    @Autowired
    private UserService userService;

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
    @RequestMapping(value="followers")
    public ModelAndView followers(HttpServletRequest request){
        /*ModelAndView mav=new ModelAndView();
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null) {
            List<Integer> followers = relationService.allFollowers(user);
            mav.addObject("followers", followers);
            mav.setViewName("relationship/followers");
        }
        return mav;*/
        ModelAndView mav=new ModelAndView();
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null) {
            List<Integer> followerids = relationService.allFollowers(user);
            List<User> Followers=new ArrayList<>();
            for(Integer id:followerids){
                UserKey key = new UserKey();
                key.setUserid(id);
                User follower  = userService.selectById(key);
                Followers.add(follower);
            }
            System.out.println(Followers);
            mav.addObject("followers", Followers);
            mav.setViewName("relationship/followers");
        }
        return mav;
    }



    /*get all followees*/
    @RequestMapping(value="followees")
    public ModelAndView followees(HttpServletRequest request){
      /*  ModelAndView mav=new ModelAndView();
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null) {
            List<Integer> followees = relationService.allFollowees(user);
            mav.addObject("followees", followees);
            mav.setViewName("relationship/following");
        }
        return mav;*/
        ModelAndView mav=new ModelAndView();
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null) {
            List<Integer> followeeids = relationService.allFollowees(user);
            List<User> Followees=new ArrayList<>();
            for(Integer id:followeeids){
                UserKey key = new UserKey();
                key.setUserid(id);
                User followee  = userService.selectById(key);
                Followees.add(followee);
            }
            System.out.println(Followees);
            mav.addObject("followees", Followees);
            mav.setViewName("relationship/following");
        }
        return mav;
    }

}
