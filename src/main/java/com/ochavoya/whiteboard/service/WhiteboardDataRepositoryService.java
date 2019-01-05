package com.ochavoya.whiteboard.service;

import com.ochavoya.whiteboard.dto.WhiteboardItemDTO;
import com.ochavoya.whiteboard.entities.UserEntity;
import com.ochavoya.whiteboard.entities.WhiteboardItemEntity;
import com.ochavoya.whiteboard.repository.UserRepository;
import com.ochavoya.whiteboard.repository.WhiteboardDataRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WhiteboardDataRepositoryService {
    private UserRepository userRepository;
    private WhiteboardDataRepository whiteboardDataRepository;

    public WhiteboardDataRepositoryService(UserRepository userRepository, WhiteboardDataRepository whiteboardDataRepository) {
        this.userRepository = userRepository;
        this.whiteboardDataRepository = whiteboardDataRepository;
    }

    @Transactional
    public List<WhiteboardItemDTO> load() {
        return whiteboardDataRepository.getWhiteboardItemEntitiesByExpiresOnBefore(new Timestamp((new Date()).getTime()))
                .stream()
                .filter(x->x.getActive())
                .map(x-> new WhiteboardItemDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional
    public WhiteboardItemDTO create(WhiteboardItemDTO whiteboardItemDTO) {
        String token = whiteboardItemDTO.getToken();
        if( token == null) {
            throw new RuntimeException("Invalid token");
        }
        UserEntity userEntity= userRepository.getUserEntityByToken(whiteboardItemDTO.getToken());
        WhiteboardItemEntity whiteboardItemEntity = new WhiteboardItemEntity(whiteboardItemDTO, userEntity.getUserId());
        whiteboardDataRepository.save(whiteboardItemEntity);
        return whiteboardItemDTO;
    }
}
