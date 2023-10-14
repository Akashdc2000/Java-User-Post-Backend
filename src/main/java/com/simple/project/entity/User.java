package com.simple.project.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "User")
public class User {
    private String id;
    private String userName;
    private String email;
    private String password;
}
