package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Post;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {

    Post createNewPost(Post post, Integer userId)throws Exception;

    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> findPostUserId(Integer userId);

    Post findPostById(Integer postId) throws Exception;

    List<Post> findAllPost();

    Post savePost(Integer postId, Integer userId) throws Exception;

    Post likePost(Integer postId, Integer userID) throws Exception;

}
