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
        try {
            return userRepositoryService.register(userRegisterDTO);
        }
        catch (RuntimeException rte) {
            return new WhiteboardResponse(false, rte.getMessage());
        }
    }

    @PostMapping("/login")
    public WhiteboardResponse login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        try {
            return userRepositoryService.login(userLoginDTO);
        }
        catch (RuntimeException rte) {
            return new WhiteboardResponse(false, GENERIC_ERROR);
        }
    }

    @PostMapping("/logout")
    public WhiteboardResponse logout(String username) {

        try {
            return userRepositoryService.logout(username);
        }
        catch (RuntimeException rte) {
            return new WhiteboardResponse(false, GENERIC_ERROR);
        }
    }

    // TODO: Finish implementing the methods create() and load()

    @PostMapping("/create")
    public WhiteboardResponse create(@Valid WhiteboardItemDTO whiteboardItemDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        try {
            return new WhiteboardResponse(true, objectMapper.writeValueAsString(whiteboardDataRepositoryService.create(whiteboardItemDTO)));
        }
        catch (RuntimeException | JsonProcessingException ex) {
            return new WhiteboardResponse(false, GENERIC_ERROR);
        }
    }

    @PostMapping("/load")
    public WhiteboardResponse load() {
        try {
            return new WhiteboardResponse(true, objectMapper.writeValueAsString(whiteboardDataRepositoryService.load()));
        }
        catch (RuntimeException | JsonProcessingException ex) {
            return new WhiteboardResponse(false, GENERIC_ERROR);
        }
    }
}
