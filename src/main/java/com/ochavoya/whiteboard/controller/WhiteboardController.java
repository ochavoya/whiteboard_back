package com.ochavoya.whiteboard.controller;

import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import com.ochavoya.whiteboard.dto.WhiteboardResponse;
import com.ochavoya.whiteboard.service.UserRepositoryService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/whiteboard")
public class WhiteboardController {
    private UserRepositoryService userRepositoryService;

    public WhiteboardController(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

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
}
