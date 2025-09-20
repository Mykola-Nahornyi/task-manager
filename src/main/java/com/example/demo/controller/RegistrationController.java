/*package com.example.demo.controller;

import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.dto.AuthDtos.RegisterRequest;


@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final AuthService authService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // имя Thymeleaf-шаблона register.html
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        RegisterRequest req = new RegisterRequest(username, password); // создаём объект
        authService.register(req); // передаём один объект
        return "redirect:/login";
    }
}


 */