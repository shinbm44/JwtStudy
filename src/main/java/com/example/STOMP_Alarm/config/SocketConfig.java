package com.example.STOMP_Alarm.config;

import com.example.STOMP_Alarm.jwt.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class SocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JWTFilter jwtFilter;
    private final JwtChannelInterceptor jwtChannelInterceptor;



    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic" ,"/queue"); // /topic 경로는 구독하는 n명에게 전체 메세지, /queue는 한 명에게
        registry.setApplicationDestinationPrefixes("/ws");// 도착 경로에 대한 prefix를 설정, 클라이언트에서 보낸 메세지를 받는 api prefix 엔드포인트
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/our-websockt")
                .setAllowedOrigins("*")
                .withSockJS();

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(jwtChannelInterceptor);
    }
}
