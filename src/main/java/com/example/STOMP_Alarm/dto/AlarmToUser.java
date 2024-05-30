package com.example.STOMP_Alarm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmToUser {
    private String title;
    private String message;
    private String recipient;
}
