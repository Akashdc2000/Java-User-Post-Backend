package com.simple.project.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "UserPost")
public class UserPost {
    private String id;
    private String email;
    private String userId;
    private String content;
}
