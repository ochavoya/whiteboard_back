package com.ochavoya.whiteboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ochavoya.whiteboard.dto.UserLoginDTO;
import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import com.ochavoya.whiteboard.dto.WhiteboardResponse;
import com.ochavoya.whiteboard.dto.WhiteboardItemDTO;
import com.ochavoya.whiteboard.service.UserRepositoryService;
import com.ochavoya.whiteboard.service.WhiteboardDataRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ResponseStatus(HttpStatus.ACCEPTED)
@RequestMapping(value = "/whiteboard", produces = "application/json")
public class WhiteboardController {
    private UserRepositoryService userRepositoryService;
    private WhiteboardDataRepositoryService whiteboardDataRepositoryService;

    public WhiteboardController(UserRepositoryService userRepositoryService, WhiteboardDataRepositoryService whiteboardDataRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.whiteboardDataRepositoryService = whiteboardDataRepositoryService;
    }

    @PostMapping("/register")
    public WhiteboardResponse register(@Valid @RequestBody UserRegisterDTO userRegisterDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        return userRepositoryService.register(userRegisterDTO);
    }

    @PostMapping("/login")
    public WhiteboardResponse login(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        return userRepositoryService.login(userLoginDTO);
    }

    @PostMapping("/logout")
    public WhiteboardResponse logout(String username) {

        return userRepositoryService.logout(username);
    }

    @PostMapping("/create")
    public WhiteboardResponse create(@Valid @RequestBody WhiteboardItemDTO whiteboardItemDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new WhiteboardResponse(false, "form has errors");
        }
        return new WhiteboardResponse(true, whiteboardDataRepositoryService.create(whiteboardItemDTO));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public WhiteboardResponse load() {
        return new WhiteboardResponse(true, whiteboardDataRepositoryService.load());
    }
}
