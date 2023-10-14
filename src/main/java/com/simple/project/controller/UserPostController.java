package com.simple.project.controller;

import com.simple.project.entity.User;
import com.simple.project.entity.UserPost;
import com.simple.project.exception.CustomException;
import com.simple.project.service.UserPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserPostController {

    private final UserPostService userPostService;

    public UserPostController(UserPostService userPostService) {
        this.userPostService = userPostService;
    }

    @GetMapping("/user-post")
    public ResponseEntity<List<UserPost>> getAllPosts() {
        return userPostService.getAllPosts();
    }

    @GetMapping("/user-post/{userId}")
    public ResponseEntity<List<UserPost>> getPostsByUserId(@PathVariable(name = "userId") String userId) {
        return userPostService.getPostsByUserId(userId);
    }

    @GetMapping("/user-post/post/{postId}")
    public ResponseEntity<UserPost> getPostByPostId(@PathVariable(name = "postId") String userId) {
        return userPostService.getPostByPostId(userId);
    }

    @PostMapping("/user-post")
    public ResponseEntity<UserPost> addNewPost(@RequestBody UserPost userPost) {
        return userPostService.addUserPost(userPost);
    }

    @PutMapping("/user-post/post/{postId}")
    public ResponseEntity<UserPost> updateUserPost(@RequestBody UserPost userPost, @PathVariable String postId) {
        if(postId == null || !postId.equals(userPost.getId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Post Id is required");
        }
        return userPostService.updateUserPost(userPost);
    }

    @DeleteMapping("/user-post/post/{postId}")
    public ResponseEntity<Void> deletePostByPostId(@PathVariable(name = "postId") String postId) {
        return userPostService.deletePostByPostId(postId);
    }
}
