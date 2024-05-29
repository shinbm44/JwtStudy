package com.example.STOMP_Alarm.repository;


import com.example.STOMP_Alarm.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
