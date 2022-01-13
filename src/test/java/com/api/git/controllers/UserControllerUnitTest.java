package com.api.git.controllers;


import com.api.git.clients.RepositoryClient;
import com.api.git.clients.UserClient;
import com.api.git.model.ResponseMother;
import com.api.git.models.RepositoryResponse;
import com.api.git.models.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import feign.Response;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {UserController.class, UserClient.class, RepositoryClient.class})
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@ActiveProfiles("test")
public class UserControllerUnitTest {

    @MockBean
    UserClient userClient;

    @MockBean
    RepositoryClient repositoryClient;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void givenUsername_whenCallingGetUser_thenReturn200_andUserResponse() throws Exception {
        UserResponse userResponse = ResponseMother.getUserResponse();
        String username = "blabla";
        String userResponseJson = mapper.writeValueAsString(userResponse);

        given(userClient.getUser(BDDMockito.any(String.class))).willReturn(userResponse);

        MvcResult mvcResult = mockMvc.perform(get("/usr/" + username))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString(UTF_8);

        BDDAssertions.assertThat(content).isEqualToIgnoringWhitespace(userResponseJson);
    }

    @Test
    void givenUsername_whenCallingGetUser_thenReturn404_andFeignException() throws Exception {

        given(userClient.getUser(BDDMockito.any(String.class))).willThrow(FeignException.class);

        mockMvc.perform(get("/usr/"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();

    }

    @Test
    void givenUsername_whenCallinggetRepositories_thenReturn200_andRepositoryResponse() throws Exception {
        List<RepositoryResponse> repositoryResponse = ResponseMother.getRepositoryResponse();
        String username = "username";
        String repositoryResponseJson = mapper.writeValueAsString(repositoryResponse);

        given(repositoryClient.getRepositories(BDDMockito.any(String.class))).willReturn(repositoryResponse);

        MvcResult mvcResult = mockMvc.perform(get("/usr/" + username + "/repos"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString(UTF_8);

        BDDAssertions.assertThat(content).isEqualTo(repositoryResponseJson);
    }

}