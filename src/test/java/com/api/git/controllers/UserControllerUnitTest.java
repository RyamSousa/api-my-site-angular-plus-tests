package com.api.git.controllers;


import com.api.git.clients.RepositoryClient;
import com.api.git.clients.UserClient;
import com.api.git.model.UserResponseMother;
import com.api.git.models.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.BDDMockito.*;

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
    void dadoUmNomeDeUsuario_quandoChamarGetUser_entaoRetornar200_eUserResponse() throws Exception {
        UserResponse userResponse = UserResponseMother.getUserResponse();
        String username = "blabla";
        String userResponseJson = mapper.writeValueAsString(userResponse);

        given(userClient.getUser(BDDMockito.any(String.class))).willReturn(userResponse);

        //given(userClient.getUser(BDDMockito.any(String.class))).willThrow(FeignException.class);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/usr/" + username))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString(UTF_8);

        BDDAssertions.assertThat(content).isEqualToIgnoringWhitespace(userResponseJson);
    }


}