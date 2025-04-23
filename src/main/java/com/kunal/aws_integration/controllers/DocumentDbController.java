package com.kunal.aws_integration.controllers;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/db")
public class DocumentDbController {

    private final MongoTemplate mongoTemplate;

    public DocumentDbController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/test-connection")
    public ResponseEntity<String> testConnection() {
        try {
            // Try listing collections in the database
            List<String> collections = mongoTemplate.getDb().listCollectionNames().into(new ArrayList<>());
            return ResponseEntity.ok("Connected to DocumentDB! Collections: " + collections);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Connection failed: " + e.getMessage());
        }
    }
}
