package com.api.git.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RepositoryResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("private")
    private String isPrivate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("language")
    private String language;
}
