package com.example.STOMP_Alarm.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Maincontroller {
    @GetMapping("/")
    public String mainP(){
        return "main Controller";
    }
}
