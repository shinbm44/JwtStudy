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

    private final JwtChannelInterceptor jwtChannelInterceptor;



    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 구독 경로 설정하는 코드
        registry.enableSimpleBroker("/topic" ,"/queue"); // /topic 경로는 구독하는 n명에게 전체 메세지, /queue는 한 명에게
        // 클라이언트가 메시지를 보낼 때 사용하는 접두사 설정 -- 이 경로로는 메세지를 보내고 핸드러로 다시 경로에 맞춰서 메세지를 보내야한다 이때 이걸 하는 역할이 messagemaping 어노테이션
        registry.setApplicationDestinationPrefixes("/app");// 도착 경로에 대한 prefix를 설정, 클라이언트에서 보낸 메세지를 받는 api prefix 엔드포인트
        registry.setUserDestinationPrefix("/user");
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(jwtChannelInterceptor);
    }
}
