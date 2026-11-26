package com.unipacifico._3market.controller.web;

import com.unipacifico._3market.dto.RegisterDto;
import com.unipacifico._3market.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

        public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "auth/register";
    }

@PostMapping("/register")
public String register(
        @Valid @ModelAttribute("registerDto") RegisterDto dto,
        BindingResult result,
        RedirectAttributes attrs) {

    if (result.hasErrors()) {
        return "auth/register";
    }

    try {
        userService.register(dto);
        attrs.addFlashAttribute(
                "success",
                "Registro exitoso. Inicia sesión con tus credenciales."
        );
        return "auth/login";

    } catch (IllegalArgumentException e) {
        
        attrs.addFlashAttribute("error", e.getMessage());
        return "auth/register";
    }
}

        }

