package com.example.STOMP_Alarm.service;

import com.example.STOMP_Alarm.entity.UserEntity;
import com.example.STOMP_Alarm.dto.joinDTO;
import com.example.STOMP_Alarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void adminJoinService(joinDTO dto) {

        String username = dto.getUsername();
        String password = dto.getPassword();

        Boolean isExists = userRepository.existsByUsername(username);

        if (isExists) {
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }

    public void userJoinService(joinDTO dto) {

        String username = dto.getUsername();
        String password = dto.getPassword();

        Boolean isExists = userRepository.existsByUsername(username);

        if (isExists) {
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
