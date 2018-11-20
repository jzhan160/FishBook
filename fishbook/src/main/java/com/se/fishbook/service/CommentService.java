package com.se.fishbook.service;


import com.se.fishbook.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> selectCommentsByPostId(Integer postId);

    void addComment(Comment comment);
}
