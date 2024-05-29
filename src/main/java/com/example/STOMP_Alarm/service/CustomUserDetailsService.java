package com.example.STOMP_Alarm.service;

import com.example.STOMP_Alarm.dto.CustomUserDetails;
import com.example.STOMP_Alarm.entity.UserEntity;
import com.example.STOMP_Alarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);

        if(user != null) {
            return new CustomUserDetails(user);
        }



        return null;
    }
}
