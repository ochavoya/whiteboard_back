package com.ochavoya.whiteboard.repository;

import com.ochavoya.whiteboard.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    List<UserEntity> getUserEntitiesByUsername(String username);
    UserEntity getUserEntityByToken(String token);
}
