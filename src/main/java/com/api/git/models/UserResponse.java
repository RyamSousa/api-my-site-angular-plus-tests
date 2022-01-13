package com.api.git.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("login")
    private String username;

    @JsonProperty("avatar_url")
    private String linkProfileImage;

    @JsonProperty("html_url")
    private String linkProfile;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("email")
    private String email;

    @JsonProperty("company")
    private String company;

    @JsonProperty("repos_url")
    private String linkRepositories;

    @JsonProperty("location")
    private String location;

    @JsonProperty("public_repos")
    private Integer publicRepos;

}
