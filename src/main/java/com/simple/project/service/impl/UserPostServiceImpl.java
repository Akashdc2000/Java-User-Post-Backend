package com.simple.project.service.impl;

import com.simple.project.entity.User;
import com.simple.project.entity.UserPost;
import com.simple.project.exception.CustomException;
import com.simple.project.repository.UserPostRepository;
import com.simple.project.repository.UserRepository;
import com.simple.project.service.UserPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPostServiceImpl implements UserPostService {

    private final UserPostRepository userPostRepository;

    private final UserRepository userRepository;

    public UserPostServiceImpl(UserPostRepository userPostRepository, UserRepository userRepository) {
        this.userPostRepository = userPostRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<UserPost>> getAllPosts() {
        return ResponseEntity.ok(userPostRepository.findAll());
    }

    @Override
    public ResponseEntity<List<UserPost>> getPostsByUserId(String userId) {
        return ResponseEntity.ok(userPostRepository.findAllByUserId(userId));
    }

    @Override
    public ResponseEntity<UserPost> getPostByPostId(String postId) {
        Optional<UserPost> userPost = userPostRepository.findById(postId);
        if(userPost.isPresent()) {
            return ResponseEntity.ok(userPost.get());
        }
        return null;
    }

    @Override
    public ResponseEntity<UserPost> addUserPost(UserPost userPost) {

        Optional<User> user = userRepository.findByEmail(userPost.getEmail());
        if(user.isPresent()) {
            userPost.setUserId(user.get().getId());
        } else {
            userPost.setUserId("Anonymous-User");
        }
        return ResponseEntity.ok(userPostRepository.save(userPost));
    }

    @Override
    public void deletePostsByUserId(String userId) {
        if(userId == null || userId.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User Id not should not be null or empty");
        }
        userPostRepository.deleteAllByUserId(userId);
    }

    @Override
    public ResponseEntity<UserPost> updateUserPost(UserPost userPost) {
        if(userPost == null || userPost.getId() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Post data is not valid");
        }
        Optional<UserPost> post = userPostRepository.findById(userPost.getId());
        if(post.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "No Post Found!!!");
        }
        return ResponseEntity.ok(userPostRepository.save(userPost));
    }

    @Override
    public ResponseEntity<Void> deletePostByPostId(String postId) {
        Optional<UserPost> post = userPostRepository.findById(postId);
        if(post.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Post data not found!!");
        }
        userPostRepository.deleteById(postId);
        return ResponseEntity.noContent().build();
    }
}
