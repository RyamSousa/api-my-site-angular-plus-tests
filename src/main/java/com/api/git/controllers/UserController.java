package com.api.git.controllers;

import com.api.git.clients.RepositoryClient;
import com.api.git.clients.UserClient;
import com.api.git.models.RepositoryResponse;
import com.api.git.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "usr")
public class UserController {

    @Autowired
    public UserClient userClient;

    @Autowired
    public RepositoryClient repositoryClient;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username){
        return ResponseEntity.ok(userClient.getUser(username));
    }

    @GetMapping("/{username}/repos")
    public ResponseEntity<List<RepositoryResponse>> getRepositories(@PathVariable String username){
        return ResponseEntity.ok(repositoryClient.getRepositories(username));
    }
}
