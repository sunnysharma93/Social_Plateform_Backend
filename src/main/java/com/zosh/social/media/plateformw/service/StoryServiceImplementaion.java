package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Story;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementaion implements StoryService {

    @Autowired
     private StoryRepository storyRepository;

    @Autowired
    private UserService userService;


    @Override
    public Story createStory(Story story, User user) {

      Story createdStory = new Story();

      createdStory.setCaption(story.getCaption());
      createdStory.setImage(story.getImage());
      createdStory.setUser(user);
      createdStory.setTimeStamp(LocalDateTime.now());

      return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        return storyRepository.findByUserId(userId);
    }
}
