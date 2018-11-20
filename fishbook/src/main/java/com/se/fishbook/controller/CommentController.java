package com.se.fishbook.controller;

import com.se.fishbook.model.Comment;
import com.se.fishbook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.se.fishbook.util.Result;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //direct to the page where you can start a new comment
    @RequestMapping("")
    public String comment(){
        return "";
    }

    //submit your new comment
    @RequestMapping("/submit_comment")
    public String submitComment(){

        return "";
    }


    @RequestMapping("/showComment")
    public @ResponseBody List<Comment> showComment(HttpServletRequest request){
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        List<Comment> comments = commentService.selectCommentsByPostId(postId);
        return comments;
    }

    //Don't look at the code below this line.
    @RequestMapping("/test/testAjax")
    public String test1(){
        return "/test/testAjax";
    }

    @RequestMapping("/test/Ajax/test")
    @ResponseBody
    public int test1(HttpServletRequest request){
        return 2;
    }
}
