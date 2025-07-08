package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.config.JwtProvider;
import com.zosh.social.media.plateformw.exception.UserException;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        User savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws UserException {


        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not exits ith userID" + userId);
    }

    @Override
    public User findUserByEmail(String email) {

        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {

        User reqUser = findUserById(reqUserId);

        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());

        reqUser.getFollowing().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserException {

        Optional<User> user1= userRepository.findById(userId);

        if(user1.isEmpty()){
            throw new UserException("user not exits "+ userId);

        }
        User oldUser = user1.get();

        if(user.getName()!=null){
            oldUser.setName(user.getName());
        }
        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getGender() != null){
            oldUser.setGender(user.getGender());
        }

        User updateUser = userRepository.save(oldUser);

        return updateUser;

    }

    @Override
    public List<User> searchUser(String query) {


        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {

        String email = JwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findByEmail(email);

        return user;
    }
}