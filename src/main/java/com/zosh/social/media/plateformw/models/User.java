package com.zosh.social.media.plateformw.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
//@GeneratedValue(strategy = GenerationType.IDENTITY)
public class User {

    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String lastName;


    private  String email;
    private  String password;

    private List<Integer> followers = new ArrayList<>();

    private List<Integer>following = new ArrayList<>();

    private String gender;

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String name, String lastName, String email, String password, List<Integer> followers, List<Integer> following, String gender) {
        this.id = id;
        this.name= name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.followers = followers;
        this.following = following;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }
}
