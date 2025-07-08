package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Comment;
import com.zosh.social.media.plateformw.models.Post;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.repository.CommentRepository;
import com.zosh.social.media.plateformw.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation  implements  CommentService{

    @Autowired
   private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment,
                                 Integer postId,
                                 Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreateAt(LocalDateTime.now());

        Comment saveComment = commentRepository.save(comment);

        post.getComments().add(saveComment);

        postRepository.save(post);

        return saveComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {

       Optional<Comment> opt = commentRepository.findById(commentId);

       if (opt.isEmpty()){
           throw new Exception("Comment not exist");
       }
        return opt.get();
    }

    @Override
    public Comment likeComment(Integer CommentId, Integer userId) throws Exception {

         Comment comment= findCommentById(CommentId);
        User user = userService.findUserById(userId);
        if(!comment.getLiked().contains(user)){
              comment.getLiked().add(user);
        }
        else comment.getLiked().remove(user);

        return commentRepository.save(comment);
    }
}
