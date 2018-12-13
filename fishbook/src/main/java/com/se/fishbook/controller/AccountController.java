//////////////////////////////////////////////////////////////
// AccountController.java  response to web requests         //
// ver 1.0                                                  //
//                                                          //
//////////////////////////////////////////////////////////////
/*
 * This package provides one Java class AccountController
 * which is responsible for the account related front end
 * request.
 *
 * */

package com.se.fishbook.controller;
import com.se.fishbook.model.*;
import com.se.fishbook.service.CommentService;
import com.se.fishbook.service.NotificationService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.service.UserService;
import com.se.fishbook.util.Constants;
import com.se.fishbook.util.DateUtil;
import com.se.fishbook.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/*
* AccountController class handles requests about user accounts including:
* see profile page, edit personal information and password, upload personal
* image, look through and delete previous posts, and load notifications.
*
*
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

    @Autowired
    private CommentService commentService;

    //direct to the profile page
    @RequestMapping("/profile")
    public ModelAndView profile(HttpServletRequest request){
        System.out.println("in profile page");
        System.out.println("user in session:"+request.getSession().getAttribute(Constants.CURRENT_USER));
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        ModelAndView mv = new ModelAndView("account/profile");
        if (user!=null){
            System.out.println("==================Loading the post...===============");
            List<Post> posts = postService.postByUserId(user.getUserid());
            System.out.println("==================Total Num:"+ posts.size() +"===============");
            Map<Integer, List<Comment>> comments = new HashMap<>();
            int i = 0;
            for(Post post : posts){
                comments.put(i,commentService.selectCommentsByPostId(post.getPostid()));
            }
            //Set the Model and View
            mv.setViewName("/account/profile");
            mv.addObject("posts", posts);
            mv.addObject("comments", comments);
            return mv;
        }
        mv.setViewName("/account/profile");
        return mv;
    }

    //delete previous posts
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


    //this is a test method to search root path of the project
    @RequestMapping("/path")
    public @ResponseBody  String pathTest( HttpServletRequest request){
        //request.getRealPath("");
        String path = request.getServletContext().getRealPath("/static");

        System.out.println(path);
/*        System.out.println(request.getSession().getServletContext().getRealPath(""));
        System.out.println(request.getSession().getServletContext().getRealPath(request.getRequestURI()));*/
        System.out.println();

        try {
            String path1 = ResourceUtils.getURL("classpath:").getPath();
            System.out.println(path1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      /*  ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            files.add(tempList[i].toString());
        }*/
        return path;

    }

    //submit a personal image
   @RequestMapping(value="/update_image")
    public String upload(@RequestParam("image")MultipartFile[] files, HttpServletRequest request) {
       User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
       String path = "";
       try {
           //send the img
           if(files!=null && files.length>=1) {
               BufferedOutputStream bw = null;
               try {
                   String fileName = files[0].getOriginalFilename();
                   //判断是否有文件
                   if(StringUtils.isNoneBlank(fileName)) {
                       String uploadPath = ResourceUtils.getURL("classpath:").getPath()+"static/image/head/";
                       String name = Constants.SF_FILE_SEPARATOR + user.getUserid()+"_"+
                               DateUtil.date2Str(new Date())+
                               FileUtil.getFileType(fileName);

                       path = uploadPath+name;
                       File outFile = new File(path);
                       FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               } finally {
                   try {
                       if(bw!=null) {bw.close();}
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
           //create a new post entry
          user.setImagepath(path);
          userService.updateUserImage(user);

       } catch (Exception e) {
           e.printStackTrace();
       }
       return "/account/profile";
    }


    //load notifications
    @RequestMapping("/notification")
    public ModelAndView notification(HttpServletRequest request){
        System.out.println("in index page");
        System.out.println("user in session:"+request.getSession().getAttribute(Constants.CURRENT_USER));
        User user = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        ModelAndView mv  = new ModelAndView();
        System.out.println("=================================");
        if (user!=null){
            List<Notification> readN = notificationService.showReadNotifications(user.getUserid());
            List<Notification> unreadN = notificationService.showUnreadNotifications(user.getUserid());
            List<NotificationDisplay> unreaNDs = new LinkedList<>();
            List<NotificationDisplay> reaNDs = new LinkedList<>();
            for(Notification n : readN) {
                NotificationDisplay nd = new NotificationDisplay();
                eventParser(n, nd);
                List<CommentDisplay> cds = new LinkedList<>();
                if(nd.getHasPost() != 0){
                    List<Comment> cs = commentService.selectCommentsByPostId(nd.getPost().getPostid());
                    for(Comment c: cs){
                        CommentDisplay cd = new CommentDisplay();
                        UserKey uk = new UserKey();
                        uk.setUserid(c.getAuthorid());
                        cd.setUser(userService.selectById(uk));
                        cd.setComment(c);
                        cds.add(cd);
                    }
                }
                nd.setComments(cds);
                reaNDs.add(nd);
            }
            for(Notification n : unreadN){
                NotificationDisplay nd = new NotificationDisplay();
                eventParser(n, nd);
                List<CommentDisplay> cds = new LinkedList<>();
                if(nd.getHasPost() != 0){
                    List<Comment> cs = commentService.selectCommentsByPostId(nd.getPost().getPostid());
                    for(Comment c: cs){
                        CommentDisplay cd = new CommentDisplay();
                        UserKey uk = new UserKey();
                        uk.setUserid(c.getAuthorid());
                        cd.setUser(userService.selectById(uk));
                        cd.setComment(c);
                        cds.add(cd);
                    }
                }
                nd.setComments(cds);
                unreaNDs.add(nd);
            }
            System.out.println("==================Total Num:"+ unreadN.size() +"===============");
            mv.setViewName("/account/notification");
            mv.addObject("unreadN", unreaNDs);
            mv.addObject("readN", reaNDs);
            return mv;
        }
        mv.setViewName("/index");
        return mv;

    }

    //eventParser for notifications
    private void eventParser(Notification n, NotificationDisplay nd){
        nd.setNotification(n);
        String[] event = n.getEvent().split(" ");
        UserKey k = new UserKey();
        k.setUserid(n.getTriggerid());
        User u = userService.selectById(k);
        nd.setTrigger(u);
        //String E = event.substring(0,1);
        switch (event[0]){
            case "follow":{
                nd.setText(u.getUsername()+" started following you.");
                nd.setHasPost(0);
                break;
            }
            case "unfollow":{
                nd.setText(u.getUsername()+" started unfollowing you.");
                nd.setHasPost(0);
                break;
            }
            case "like":{
                nd.setText(u.getUsername()+" liked your post.");
                nd.setPost(postService.selectByPostId(Integer.parseInt(event[1])));
                nd.setHasPost(1);
                break;
            }
            case "comment":{
                nd.setText(u.getUsername()+" commented on your post.");
                nd.setPost(postService.selectByPostId(Integer.parseInt(event[1])));
                nd.setHasPost(1);
                break;
            }
        }
    }

}
