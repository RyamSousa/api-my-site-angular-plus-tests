package com.api.git.clients;

import com.api.git.models.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "user", url = "https://api.github.com/users/")
public interface UserClient {

    @RequestMapping(method = GET, value = "/{username}", produces = APPLICATION_JSON_VALUE)
    public UserResponse getUser(@PathVariable String username);

}
