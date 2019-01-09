package com.ochavoya.whiteboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ochavoya.whiteboard.dto.UserLoginDTO;
import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import com.ochavoya.whiteboard.dto.WhiteboardResponse;
import com.ochavoya.whiteboard.dto.WhiteboardItemDTO;
import com.ochavoya.whiteboard.service.UserRepositoryService;
import com.ochavoya.whiteboard.service.WhiteboardDataRepositoryService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/whiteboard")
public class WhiteboardController {
    private static final String GENERIC_ERROR = "There was a serve error while processing your request";
    private UserRepositoryService userRepositoryService;
    private WhiteboardDataRepositoryService whiteboardDataRepositoryService;
    private ObjectMapper objectMapper;


    public WhiteboardController(UserRepositoryService userRepositoryService, WhiteboardDataRepositoryService whiteboardDataRepositoryService, ObjectMapper objectMapper) {
        this.userRepositoryService = userRepositoryService;
        this.whiteboardDataRepositoryService = whiteboardDataRepositoryService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/register")
    public WhiteboardResponse register(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        return userRepositoryService.register(userRegisterDTO);
    }

    @PostMapping("/login")
    public WhiteboardResponse login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        return userRepositoryService.login(userLoginDTO);
    }

    @PostMapping("/logout")
    public WhiteboardResponse logout(String username) {

        return userRepositoryService.logout(username);
    }

    @PostMapping("")
    public WhiteboardResponse create(@Valid WhiteboardItemDTO whiteboardItemDTO, BindingResult bindingResult)
    throws JsonProcessingException{
        if(bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        return new WhiteboardResponse(true, objectMapper.writeValueAsString(whiteboardDataRepositoryService.create(whiteboardItemDTO)));
    }

    @GetMapping("")
    public WhiteboardResponse load() throws JsonProcessingException {
        return new WhiteboardResponse(true, objectMapper.writeValueAsString(whiteboardDataRepositoryService.load()));
    }
}
