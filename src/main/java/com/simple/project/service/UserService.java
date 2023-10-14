package com.simple.project.service;

import com.simple.project.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserByUserId(String userId);

    ResponseEntity<User> addUser(User user);

    ResponseEntity<User> updateUser(User user);

    ResponseEntity<Void> deleteUserByUserId(String userId);

    Optional<User> findByEmail(String username);
}
