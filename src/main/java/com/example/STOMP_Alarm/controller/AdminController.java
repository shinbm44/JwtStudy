package com.example.STOMP_Alarm.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String mainP(){
        return "admin Controller";
    }


}
