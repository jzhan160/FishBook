package com.se.fishbook.controller;

import com.se.fishbook.model.Notification;
import com.se.fishbook.model.Post;
import com.se.fishbook.model.User;
import com.se.fishbook.service.NotificationService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.test.Location;
import com.se.fishbook.util.Constants;
import com.se.fishbook.util.DateUtil;
import com.se.fishbook.util.FileUtil;
import com.se.fishbook.util.Result;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    @Value("${web.upload.path}")
    private String uploadPath;


    //submit your new post
    @RequestMapping(value="/submit_post")
    public String submitPost(@RequestParam("fishing_pic")MultipartFile[] files, @RequestParam String content, HttpServletRequest request) {
        Result result = new Result();
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
      /*  System.out.println("path:"+request.getServletContext().getContextPath());
        String uploadPath = request.getServletContext().getRealPath("/")+"post/";*/
        String path = "";
        String savedPath = "";
        try {
            //send the img
            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    //判断是否有文件
                    if(StringUtils.isNoneBlank(fileName)) {

                        String name =  user.getUserid()+"_"+
                                       DateUtil.date2Str(new Date())+ "_"+ UUID.randomUUID().toString()+
                                       FileUtil.getFileType(fileName);

                        path = uploadPath+"post/"+name;
                        System.out.println(path);
                        savedPath = "post/"+name;
                        File outFile = new File(path);
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                    }
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
            //create a new post entry
            Post post = new Post();
            post.setContent(content);
            post.setCreatetime(DateUtil.getTimestamp());
            post.setImagepath(path);
            post.setAuthorid(user.getUserid());
            Location location = (Location) request.getSession().getAttribute(Constants.CURRENT_LOCATION);
            post.setLocationlatitude(location.getLat());
            post.setLocationlongitude(location.getLng());
            post.setLikecount(0);
            System.out.println(post);
            postService.insert(post);
            result.setCode(Constants.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constants.ERROR);
        }
        return "redirect:/index";
    }



    @RequestMapping("/addLikes")
    @ResponseBody
    public Integer addLikes(HttpServletRequest request){
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        Integer val = postService.addLikesCountByOne(postId);
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        //if(postService.selectByPostId(postId).getAuthorid() != user.getUserid())
        notificationService.newLikes(postId, user.getUserid());
        return val;
    }

    @RequestMapping("/removeLikes")
    @ResponseBody
    public Integer removeLikes(HttpServletRequest request){
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        Integer val = postService.removeLikesCountByOne(postId);
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        //if(postService.selectByPostId(postId).getAuthorid() != user.getUserid())
        notificationService.newLikes(postId, user.getUserid());
        return val;
    }

}
