package com.zosh.social.media.plateformw.service;
import com.zosh.social.media.plateformw.models.Reels;
import com.zosh.social.media.plateformw.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface ReelsService {


    public Reels createReel(Reels reel,User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReels(Integer userId) throws Exception;
}
