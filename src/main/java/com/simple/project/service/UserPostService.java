package com.simple.project.service;

import com.simple.project.entity.UserPost;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserPostService {

    ResponseEntity<List<UserPost>> getAllPosts();

    ResponseEntity<List<UserPost>> getPostsByUserId(String userId);

    ResponseEntity<UserPost> getPostByPostId(String postId);

    ResponseEntity<UserPost> addUserPost(UserPost userPost);

    void deletePostsByUserId(String userId);

    ResponseEntity<UserPost> updateUserPost(UserPost userPost);

    ResponseEntity<Void> deletePostByPostId(String postId);
}
