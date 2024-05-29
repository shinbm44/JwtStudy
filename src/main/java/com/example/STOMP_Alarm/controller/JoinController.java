package com.example.STOMP_Alarm.controller;

import com.example.STOMP_Alarm.joinDTO.joinDTO;
import com.example.STOMP_Alarm.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;


    @PostMapping("/adminjoin")
    public ResponseEntity<String> adminjoin(@RequestBody joinDTO joinDTO) {

        joinService.adminJoinService(joinDTO);


        return ResponseEntity.status(201).body("회원가입성공");
    }


    @PostMapping("/userjoin")
    public ResponseEntity<String> userjoin(@RequestBody joinDTO joinDTO) {

        joinService.userJoinService(joinDTO);


        return ResponseEntity.status(201).body("회원가입성공");
    }

}
