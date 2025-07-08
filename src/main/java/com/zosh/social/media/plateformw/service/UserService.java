package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.exception.UserException;
import com.zosh.social.media.plateformw.models.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public User findUserById(Integer userId) throws UserException;

    public User findUserByEmail(String email);

    public User followUser(Integer userId1, Integer userId2) throws UserException;

    public User updateUser( User user,Integer userId) throws UserException;

    public List<User>searchUser(String query);

    public User findUserByJwt(String jwt);
}
