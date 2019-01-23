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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/whiteboard")
public class WhiteboardController {
    private UserRepositoryService userRepositoryService;
    private WhiteboardDataRepositoryService whiteboardDataRepositoryService;
    private ObjectMapper objectMapper;


    public WhiteboardController(UserRepositoryService userRepositoryService, WhiteboardDataRepositoryService whiteboardDataRepositoryService, ObjectMapper objectMapper) {
        this.userRepositoryService = userRepositoryService;
        this.whiteboardDataRepositoryService = whiteboardDataRepositoryService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/register")
    public WhiteboardResponse register(@Valid @RequestBody UserRegisterDTO userRegisterDTO, BindingResult bindingResult) {
        System.out.println("WhiteboardController.register() - " + userRegisterDTO.getName() + " - " + userRegisterDTO.getUsername());
        if(bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        return userRepositoryService.register(userRegisterDTO);
    }

    @PostMapping("/login")
    public WhiteboardResponse login(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        return userRepositoryService.login(userLoginDTO);
    }

    @PostMapping("/logout")
    public WhiteboardResponse logout(String username) {

        return userRepositoryService.logout(username);
    }

    @PostMapping("/create")
    public WhiteboardResponse create(@Valid @RequestBody WhiteboardItemDTO whiteboardItemDTO, BindingResult bindingResult)
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
