package com.ochavoya.whiteboard.service;

import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import com.ochavoya.whiteboard.dto.WhiteboardResponse;
import com.ochavoya.whiteboard.entities.UserEntity;
import com.ochavoya.whiteboard.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserRepositoryService {
    private UserRepository userRepository;

    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public WhiteboardResponse register(UserRegisterDTO userRegisterDTO) {
        List<UserEntity> userEntityList = userRepository.getUserEntitiesByUsername(userRegisterDTO.getUsername());
        switch (userEntityList.size()) {
            case 0:
                UserEntity userEntity = new UserEntity(userRegisterDTO);
                userRepository.save(userEntity);
                return new WhiteboardResponse(true, String.format("User %s was successfully registered.", userRegisterDTO.getUsername()));
            case 1:
            return new WhiteboardResponse(false, String.format("Username %s is already taken.", userRegisterDTO.getUsername()));
        }
        throw new RuntimeException("Error: unexpected");
    }
}
