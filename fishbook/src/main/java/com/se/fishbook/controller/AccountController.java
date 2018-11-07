package com.se.fishbook.controller;
import com.se.fishbook.model.Comment;
import com.se.fishbook.model.Notification;
import com.se.fishbook.model.Post;
import com.se.fishbook.model.User;
import com.se.fishbook.service.CommentService;
import com.se.fishbook.service.NotificationService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.service.UserService;
import com.se.fishbook.util.Constants;
import com.se.fishbook.util.FileUtil;
import com.se.fishbook.util.Result;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            mv.setViewName("/index");
            mv.addObject("posts", posts);
            mv.addObject("comments", comments);
            return mv;
        }
        mv.setViewName("/index");
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
   @RequestMapping(value="update_image")
    public @ResponseBody
    Result upload(@RequestParam("file")MultipartFile[] files, HttpServletRequest request) {
       System.out.println("upload personal image");
        Result result = new Result();
        try {
            User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    //判断是否有文件(实际生产中要判断是否是音频文件)
                    if(StringUtils.isNoneBlank(fileName)) {
                        String name = Constants.SF_FILE_SEPARATOR + user.getUserid()+ FileUtil.getFileType(fileName);
                        //创建输出文件对象
                        //String uploadPath = ResourceUtils.getURL("classpath:").getPath()+"\\static\\image\\";
                       // File outFile = new File(uploadPath + Constants.AVATAR_PATH + name);
                        File outFile = new File("D://GitHub//FishBook//fishbook//"+name);
                        //拷贝文件到输出文件对象
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);

                        //user.setImagepath("/image/"+Constants.AVATAR_PATH + name);
                       // memberRepository.save(member);

                    }
                    result.setCode(Constants.SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    result.setCode(Constants.ERROR);
                } finally {
                    try {
                        if(bw!=null) {bw.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constants.ERROR);
        }
        return result;
    }


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
            System.out.println("==================Total Num:"+ unreadN.size() +"===============");
            mv.setViewName("/account/notification");
            mv.addObject("unreadNotifications", unreadN);
            mv.addObject("readNotifications", readN);
            return mv;
        }
        mv.setViewName("/index");
        return mv;

    }

}
