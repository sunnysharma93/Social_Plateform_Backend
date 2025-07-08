package com.zosh.social.media.plateformw.repository;

import com.zosh.social.media.plateformw.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Integer> {


}
