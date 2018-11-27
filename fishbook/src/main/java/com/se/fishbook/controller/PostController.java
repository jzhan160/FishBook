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

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;


    //submit your new post
    @RequestMapping(value="/submit_post")
    public String submitPost(@RequestParam("fishing_pic")MultipartFile[] files, @RequestParam String content, HttpServletRequest request) {
        Result result = new Result();
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
                        String uploadPath = ResourceUtils.getURL("classpath:").getPath()+"static/image/post/";
                        String name = Constants.SF_FILE_SEPARATOR + user.getUserid()+"_"+
                                       DateUtil.date2Str(new Date())+
                                       FileUtil.getFileType(fileName);

                        path = uploadPath+name;
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
            post.setCreatetime(new Date());
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
        return "/index";
    }



    @RequestMapping("/addLikes")
    @ResponseBody
    public Integer addLikes(HttpServletRequest request){
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        Integer val = postService.addLikesCountByOne(postId);
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        notificationService.newLikes(postId, user.getUserid());
        return val;
    }
}
