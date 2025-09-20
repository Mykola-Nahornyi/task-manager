package com.example.demo.controller;

import com.example.demo.dto.AuthDtos.RegisterRequest;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthWebController {

    private final AuthService authService;

    // Стартовая страница с выбором Login / Register
    @GetMapping("/")
    public String homePage() {
        return "index"; // index.html
    }

    // Страница логина
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "login"; // login.html
    }

    // Страница регистрации (форма)
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new RegisterRequest("", ""));
        return "register"; // register.html
    }

    // Обработка регистрации
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RegisterRequest user,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "register"; // если валидация не прошла
        }
        try {
            authService.register(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // username занят
            return "register";
        }
        model.addAttribute("message", "Registration successful! Please login.");
        return "login"; // редирект на страницу логина с сообщением
    }
}
