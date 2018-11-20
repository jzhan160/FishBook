package com.se.fishbook.service;
import com.se.fishbook.model.Post;
import java.util.List;

public interface PostService {

    List<Post> postsByUserIds(List<Integer> ids);

    List<Post> postByUserId(Integer id);

    Integer addLikesCountByOne(Integer id);
}
