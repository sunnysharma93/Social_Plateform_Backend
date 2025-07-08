package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Story;
import com.zosh.social.media.plateformw.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story  , User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
