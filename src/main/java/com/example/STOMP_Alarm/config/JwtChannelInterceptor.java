package com.example.STOMP_Alarm.config;

import com.example.STOMP_Alarm.jwt.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

// STOMP 메세지를 전송하기 전과 후에 특정 로직을 처리할 수 있는 것
@RequiredArgsConstructor
@Component
public class JwtChannelInterceptor implements ChannelInterceptor {

    private final JWTUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!0----------------------------------------------------");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        System.out.println("message: " + message);
        System.out.println("헤더 : " + message.getHeaders());
        System.out.println("토큰 : " + accessor.getNativeHeader("Authorization"));

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Invalid JWT Token");
                }
            } else {
                throw new IllegalArgumentException("Authorization header must be provided and start with Bearer");
            }
        }

        return message;
    }
}
