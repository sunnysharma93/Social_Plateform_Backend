package com.zosh.social.media.plateformw.controller;

import com.zosh.social.media.plateformw.models.Reels;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.service.ReelsService;
import com.zosh.social.media.plateformw.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

@PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel ,
                             @RequestHeader("Authorization") String jwt){

        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.createReel(reel,reqUser);

        return createdReels ;
    }
    @GetMapping("/api/reels")
    public List<Reels> findAllReels(){

        List<Reels> reels = reelsService.findAllReels();

        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReelss(@PathVariable Integer userId) throws Exception {

        List<Reels> reels = reelsService.findUsersReels(userId);

        return  reels;
    }
}
