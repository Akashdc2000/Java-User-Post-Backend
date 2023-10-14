package com.simple.project.service.generic;

import com.simple.project.exception.CustomException;
import com.simple.project.repository.UserPostRepository;
import com.simple.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ManagementService {

    private final UserRepository userRepository;
    private final UserPostRepository userPostRepository;

    public ManagementService(UserRepository userRepository, UserPostRepository userPostRepository) {
        this.userRepository = userRepository;
        this.userPostRepository = userPostRepository;
    }


    public void deletePostsByUserId(String userId) {
        if(userId == null || userId.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User Id not should not be null or empty");
        }
        userPostRepository.deleteAllByUserId(userId);
    }
}
