package com.zosh.social.media.plateformw.controller;

import com.zosh.social.media.plateformw.models.Post;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.reponse.ApiResponse;
import com.zosh.social.media.plateformw.service.PostService;
import com.zosh.social.media.plateformw.service.UserService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestBody  Post post, @RequestHeader ("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        Post createPost = postService.createNewPost(post,reqUser.getId());
        return new ResponseEntity<>(createPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader ("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
      String message = postService.deletePost(postId,reqUser.getId());
     ApiResponse res = new ApiResponse(message,true);


        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);


    }


    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post>fingPostByIdHandler(@PathVariable Integer postId ) throws Exception {

        Post post = postService.findPostById(postId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>>findUserPost(@PathVariable Integer userId){

        List<Post>posts=postService.findPostUserId(userId);

        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);

    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>>findAllPost(){

        List<Post>posts=postService.findAllPost();

        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);

    }
    @PutMapping("/api//posts/save/{postId}")
    public ResponseEntity<Post>savedPostHandler(@PathVariable Integer postId, @RequestHeader ("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savePost(postId,reqUser.getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }
    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post>likePostHandler(@PathVariable Integer postId, @RequestHeader ("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId,reqUser.getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }




}
