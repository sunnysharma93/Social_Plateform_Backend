package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Post;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.repository.PostRepository;
import com.zosh.social.media.plateformw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreateAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
       if(post.getUser().getId()!=user.getId()){
           throw new Exception("you cnt-t delete another users post ");
       }
       postRepository.delete(post);

        return "post delete successfully";
    }

    @Override
    public List<Post> findPostUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }


    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt = postRepository.findById(postId);
        if(opt.isEmpty()){
            throw  new Exception("post not found wiht id " + postId);
        }
        return opt.get();
  
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }


    @Override
    public Post savePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        else{
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userID) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userID);

        if (post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }
        else{
            post.getLiked().add(user);
        }

        post.getLiked().add(user);


        return postRepository.save(post);
    }
}
