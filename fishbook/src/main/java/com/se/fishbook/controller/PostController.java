package com.se.fishbook.controller;

import com.se.fishbook.model.Notification;
import com.se.fishbook.model.Post;
import com.se.fishbook.model.User;
import com.se.fishbook.service.NotificationService;
import com.se.fishbook.service.PostService;
import com.se.fishbook.util.Constants;
import com.se.fishbook.util.DateUtil;
import com.se.fishbook.util.FileUtil;
import com.se.fishbook.util.Result;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    //direct to the page where you can start a new post
    @RequestMapping("")
    public String post(){
        return "";
    }

    //submit your new post
    @RequestMapping(value="/submit_post")
    public @ResponseBody
    Result submitPost(@RequestParam("fishing_pic")MultipartFile[] files, @RequestParam String content, HttpServletRequest request) {
        Result result = new Result();
        System.out.println(content);
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
                        String path = "E:\\"+name;
                        File outFile = new File(path);
                        //拷贝文件到输出文件对象
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                        Post post = new Post();
                        post.setContent(content);
                        post.setCreatetime(new Date());
                        post.setImagepath(path);
                        post.setAuthorid(user.getUserid());
                        post.setLocationlatitude(23.3545);
                        post.setLocationlongitude(-45.3958);
                        post.setLikecount(0);
                        System.out.println(post);
                         postService.insert(post);


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
