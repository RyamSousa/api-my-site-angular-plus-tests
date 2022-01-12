package com.api.git.clients;

import com.api.git.models.RepositoryResponse;
import com.api.git.models.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "repository", url = "https://api.github.com/users")
public interface RepositoryClient {

    @RequestMapping(method = GET, value = "/{username}/repos", produces = APPLICATION_JSON_VALUE)
    public List<RepositoryResponse> getRepositories(@PathVariable String username);

}
