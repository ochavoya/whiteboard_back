package com.ochavoya.whiteboard.repository;

import com.ochavoya.whiteboard.entities.WhiteboardItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface WhiteboardDataRepository extends JpaRepository<WhiteboardItemEntity, Integer> {
    List<WhiteboardItemEntity> getWhiteboardItemEntitiesByExpiresOnBefore(Timestamp timestamp);
}
