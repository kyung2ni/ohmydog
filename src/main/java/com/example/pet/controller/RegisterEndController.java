package com.example.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterEndController {

    @GetMapping("/register_end")
    public String registerEnd() {
        return "register_end"; // register_end.html로 이동
    }
}
