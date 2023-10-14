package com.simple.project.service.impl;

import com.simple.project.entity.User;
import com.simple.project.exception.CustomException;
import com.simple.project.repository.UserRepository;
import com.simple.project.service.UserService;
import com.simple.project.service.generic.ManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ManagementService managementService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ManagementService managementService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.managementService = managementService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity<User> getUserByUserId(String userId) {
        if(userId == null || userId.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "UserId is Not null or empty");
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "User not Found !!!");
        }
        return ResponseEntity.ok(user.get());
    }

    @Override
    public ResponseEntity<User> addUser(User user) {
        if(user == null || user.getId()!=null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User may not required id");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<User> updateUser(User user) {
        if(user == null || user.getId() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User id is required");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<Void> deleteUserByUserId(String userId) {
        if(userId == null || userId.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "UserId is Not null or empty");
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "User not Found !!!");
        }
        //First delete all Post associated to a userId
        managementService.deletePostsByUserId(userId);
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
