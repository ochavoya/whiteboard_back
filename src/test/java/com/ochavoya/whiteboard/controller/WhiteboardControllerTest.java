package com.ochavoya.whiteboard.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ochavoya.whiteboard.service.UserRepositoryService;
import com.ochavoya.whiteboard.service.WhiteboardDataRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@WebMvcTest(WhiteboardController.class)
@AutoConfigureMockMvc
public class WhiteboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WhiteboardDataRepositoryService whiteboardDataRepositoryService;

    @MockBean
    private UserRepositoryService userRepositoryService;

    @Autowired
    public ObjectMapper objectMapper;


    @Test
    public void test_mockMvcNotNull () {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(whiteboardDataRepositoryService);
        assertNotNull(userRepositoryService);
    }
}