package com.ochavoya.whiteboard.service;

import com.ochavoya.whiteboard.dto.UserLoginDTO;
import com.ochavoya.whiteboard.dto.UserRegisterDTO;
import com.ochavoya.whiteboard.dto.WhiteboardResponse;
import com.ochavoya.whiteboard.entities.UserEntity;
import com.ochavoya.whiteboard.repository.UserRepository;
import com.ochavoya.whiteboard.utils.Utils;
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
                return new WhiteboardResponse(true, String.format("User %s was successfully registered", userRegisterDTO.getUsername()));
            case 1:
                return new WhiteboardResponse(false, String.format("Username %s is already taken", userRegisterDTO.getUsername()));
            default:
                return new WhiteboardResponse(false, "Unknown error condition");
        }
    }

    @Transactional
    public WhiteboardResponse login(UserLoginDTO userLoginDTO) {
        List<UserEntity> userEntityList = userRepository.getUserEntitiesByUsername(userLoginDTO.getUsername());
        switch (userEntityList.size()) {
            case 0:
                return new WhiteboardResponse(false, String.format("User %s does not exist", userLoginDTO.getUsername()));
            case 1:
                UserEntity userEntity = userEntityList.get(0);
                if (!userEntity.getActive()) {
                    return new WhiteboardResponse(false, String.format("User %s is inactive", userLoginDTO.getUsername()));
                }
                if(! Utils.validate(userLoginDTO.getPassword(), userEntity.getPassword())) {
                    return new WhiteboardResponse(false, String.format("Wrong username/password", userLoginDTO.getUsername()));
                }
                String token = Utils.createToken();
                userEntity.setToken(token);
                return new WhiteboardResponse(true, token);
            default:
                return new WhiteboardResponse(false, "Unknown error condition");
        }
    }

    @Transactional
    public WhiteboardResponse logout(String username) {
        List<UserEntity> userEntityList = userRepository.getUserEntitiesByUsername(username);
        switch (userEntityList.size()) {
            case 1:
                UserEntity userEntity = userEntityList.get(0);
                userEntity.setToken(null);
                return new WhiteboardResponse(false, String.format("User %s was successfully logged out", username));
            default:
            return new WhiteboardResponse(false, "Unknown error condition");
        }
    }

}
