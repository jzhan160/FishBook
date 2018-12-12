package com.se.fishbook.service;
import com.se.fishbook.model.Post;
import javafx.geometry.Pos;

import java.util.List;

public interface PostService {
    List<Post> selectAll();

    List<Post> postsByUserIds(List<Integer> ids);

    List<Post> postByUserId(Integer id);

    Integer addLikesCountByOne(Integer id);

    Integer removeLikesCountByOne(Integer id);

    Integer insert(Post post);

    List<Post> selectPostsByLocation(double[] locationLatitude, double[] locationLongitude);

    Post selectByPostId(Integer id);


}
