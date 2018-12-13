//////////////////////////////////////////////////////////////
// CommentController.java  response to web requests         //
// ver 1.0                                                  //
//                                                          //
//////////////////////////////////////////////////////////////
/*
 * This package provides one Java class CommentController
 * which is responsible for the comment related front end
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.se.fishbook.util.Result;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
* CommentController class is the controller that manages requests about user comments, including
* committing a new comment and seeing other comments;
*
*
* */

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    //direct to the page where you can start a new comment
    @RequestMapping("")
    public String comment(){
        return "";
    }

    //submit your new comment
    @RequestMapping("/submitcomment")
    public @ResponseBody List<CommentDisplay> submitComment(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        System.out.println("------add comment------------" + postId);
        //TODO: add notification
        String content = request.getParameter("content");
        Comment comment = new Comment();
        comment.setPostid(postId);
        comment.setContent(content);
        comment.setCreatetime(DateUtil.getTimestamp());
        comment.setAuthorid(user.getUserid());
        commentService.addComment(comment);
        System.out.println("------add comment------------" + postId);
        //show all the comments
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
        //if(postService.selectByPostId(postId).getAuthorid() != user.getUserid())
        notificationService.newComments(postId, user.getUserid());
        return cd;
    }

    @RequestMapping("/showComment")
    public @ResponseBody List<CommentDisplay> showComment(HttpServletRequest request){
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        List<Comment> comments = commentService.selectCommentsByPostId(postId);
        List<CommentDisplay> cd = new ArrayList<>();
        System.out.println("------asdasdasdas--------------" + postId);
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
