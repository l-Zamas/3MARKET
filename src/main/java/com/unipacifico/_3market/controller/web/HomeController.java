package com.unipacifico._3market.controller.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.unipacifico._3market.dto.UserDto;
import com.unipacifico._3market.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

     @GetMapping({"/", "/home"})
    public String home() {
        Optional<UserDto> usuriousOpt = userService.getUsersAuthention();

        if (usuriousOpt.isPresent()) {
            UserDto userDto = usuriousOpt.get();

            if (userDto.hasRole("ROLE_ADMIN")) {
                return "admin/dashboard";
            }
            if (userDto.hasRole("ROLE_VENDEDOR")) {
                return "redirect:/vendedor/products/new";
            }
        }

        return "redirect:/catalog";
    }
    
}
