package com.se.fishbook.controller;

import com.se.fishbook.model.Comment;
import com.se.fishbook.model.CommentDisplay;
import com.se.fishbook.model.User;
import com.se.fishbook.model.UserKey;
import com.se.fishbook.service.CommentService;
import com.se.fishbook.service.UserService;
import com.se.fishbook.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.se.fishbook.util.Result;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    //direct to the page where you can start a new comment
    @RequestMapping("")
    public String comment(){
        return "";
    }

    //submit your new comment
    @RequestMapping("/submit_comment")
    public @ResponseBody List<CommentDisplay> submitComment(Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        comment.setAuthorid(user.getUserid());
        //TODO: other arguments in comment?
        commentService.addComment(comment);

        //show all the comments
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        List<Comment> comments = commentService.selectCommentsByPostId(postId);
        List<CommentDisplay> cd = new ArrayList<>();
        for(Comment c : comments) {
            CommentDisplay cc = new CommentDisplay();
            cc.setComment(c);
            UserKey uk = new UserKey();
            uk.setUserid(c.getAuthorid());
            cc.setUser(userService.selectById(uk));
            cd.add(cc);
        }
        return cd;
    }

    @RequestMapping("/showComment")
    public @ResponseBody List<CommentDisplay> showComment(HttpServletRequest request){
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        List<Comment> comments = commentService.selectCommentsByPostId(postId);
        List<CommentDisplay> cd = new ArrayList<>();
        //System.out.println("------asdasdasdas--------------");
        for(Comment c : comments){
            CommentDisplay cc = new CommentDisplay();
            cc.setComment(c);
            UserKey uk = new UserKey();
            uk.setUserid(c.getAuthorid());
            cc.setUser(userService.selectById(uk));
            cd.add(cc);
        }
        return cd;
    }

    //Test
    @RequestMapping("/testComment")
    public String testComment(){
        return "/test/testComment";
    }

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
