package com.zosh.social.media.plateformw.controller;

import com.zosh.social.media.plateformw.models.Comment;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.service.CommentService;
import com.zosh.social.media.plateformw.service.CommentServiceImplementation;
import com.zosh.social.media.plateformw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    CommentServiceImplementation commentServiceImplementation;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment , @RequestHeader("Authorization") String jwt
    , @PathVariable("postId") Integer postId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment createdComment =  commentService.createComment(comment,
                postId,
                user.getId());

        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt
            , @PathVariable Integer commentId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment likeComment =  commentService.likeComment(commentId,user.getId());

        return likeComment;
    }
}
