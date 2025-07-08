package com.zosh.social.media.plateformw.repository;

import com.zosh.social.media.plateformw.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story , Integer> {


    public List<Story> findByUserId(Integer userId);
}
