package com.simple.project.repository;

import com.simple.project.entity.UserPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserPostRepository extends MongoRepository<UserPost, String> {
    List<UserPost> findAllByUserId(String userId);

    void deleteAllByUserId(String userId);
}
