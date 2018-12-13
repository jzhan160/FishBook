package com.se.fishbook.controller;

import com.se.fishbook.model.User;
import com.se.fishbook.model.UserKey;
import com.se.fishbook.service.NotificationService;
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

/*
* RelationController will manage requests about following and unfollowing
* other users as well as looking through your followers and followings
*
* */

@Controller
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    //click the follow button to follow a user
    @RequestMapping("/follow")
    public ModelAndView follow(HttpServletRequest request){
        System.out.println("follow...");
        ModelAndView mav=new ModelAndView();
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        Integer followerId = Integer.valueOf(request.getParameter("followerId"));

        if(user!=null) {
            relationService.addRelationship(user.getUserid(),followerId);
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
            notificationService.newFollow(user.getUserid(),followerId);
        }
        return mav;
    }

    //click the follow button to unfollow a user
    @RequestMapping("/unfollow")
    public ModelAndView unfollow(HttpServletRequest request){
        System.out.println("unfollow...");
        ModelAndView mav=new ModelAndView();

        Integer followeeId = Integer.valueOf(request.getParameter("followeeid"));

        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if(user!=null) {
            relationService.deleteRelationship(user.getUserid(),followeeId);
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
            notificationService.newUnfollow(user.getUserid(),followeeId);
        }
        return mav;
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
