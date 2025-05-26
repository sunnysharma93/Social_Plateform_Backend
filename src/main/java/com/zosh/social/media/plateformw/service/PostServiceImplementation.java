package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Post;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
    //    newPost.setCreateAt(new);
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        return newPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
      
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
    public Post savePost(Integer postId, Integer userId) {
        return null;
    }

    @Override
    public Post likePost(Integer postId, Integer userID) {
        return null;
    }
}
