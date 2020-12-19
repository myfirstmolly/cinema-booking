package com.rita.cinema.api;

import com.rita.cinema.domain.User;
import com.rita.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("register")
    public String registration() {
        return "registration";
    }

    @PostMapping("register")
    public String register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "birthDate", required = false) Date birthDate,
                           Map<String, Object> model) {
        userService.add(username, password, name, email, birthDate);
        return "redirect:/login";
    }
}
