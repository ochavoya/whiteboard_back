package com.ochavoya.whiteboard.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import com.ochavoya.whiteboard.service.UserRepositoryService;
import com.ochavoya.whiteboard.service.WhiteboardDataRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WhiteboardController.class)
@AutoConfigureMockMvc
@Transactional
public class WhiteboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private WhiteboardDataRepositoryService whiteboardDataRepositoryService;

    @MockBean
    private UserRepositoryService userRepositoryService;

    @Autowired
    public ObjectMapper objectMapper;


    private UserRegisterDTO userRegisterDTO = new UserRegisterDTO();

    {

        userRegisterDTO.setUsername("__test__");
        userRegisterDTO.setName("John Doe");
        userRegisterDTO.setPassword("password");
    }


    @Test
    public void test_mockMvcNotNull () {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(whiteboardDataRepositoryService);
        assertNotNull(userRepositoryService);
    }

    @Test
    public void registerValidUser() throws Exception {

        mockMvc.perform(post("/whiteboard/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content((objectMapper.writeValueAsString(userRegisterDTO)))
        ).andExpect(status().isAccepted());
    }

    @Test
    @Transactional
    public void testLogin() throws Exception {

        userRepositoryService.register(userRegisterDTO);
        mockMvc.perform(post("/whiteboard/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"__test__\", \"password\" : \"password\" }")
        ).andExpect(status().isAccepted());

    }


}