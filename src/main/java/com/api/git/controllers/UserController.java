package com.api.git.controllers;

import com.api.git.clients.RepositoryClient;
import com.api.git.clients.UserClient;
import com.api.git.models.ExceptionResponse;
import com.api.git.models.RepositoryResponse;
import com.api.git.models.UserResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;

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

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(FeignException exception){
        return ResponseEntity.status(NOT_FOUND)
                .contentType(APPLICATION_JSON)
                .body(new ExceptionResponse(LocalDateTime.now(), "Usuário não encontrado"));
    }

}
