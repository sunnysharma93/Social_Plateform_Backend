package com.zosh.social.media.plateformw.repository;

import com.zosh.social.media.plateformw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User , Integer> {


    public User findByEmail(String email);

    @Query("select u from User u where u.name LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    public List<User>searchUser(@Param("query") String query);

}
