package com.example.STOMP_Alarm.controller;

import com.example.STOMP_Alarm.dto.Alarm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AlarmController {

    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send-alarm")
    public ResponseEntity<?> sendAlarm(@RequestBody Alarm alarm) {
        // 클라이언트로 알람 메시지를 브로드캐스트
        messagingTemplate.convertAndSend("/topic/alarms", alarm);
        return ResponseEntity.ok().build();
    }
}
