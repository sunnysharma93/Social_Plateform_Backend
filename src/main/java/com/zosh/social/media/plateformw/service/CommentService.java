package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Comment;
import org.hibernate.annotations.Comments;

public interface CommentService {

    public Comment createComment(
            Comment comment,
            Integer postId,
            Integer userId) throws Exception;

    public  Comment findCommentById(Integer commentId ) throws Exception;

    public Comment likeComment(Integer CommentId, Integer userId) throws Exception;


}
