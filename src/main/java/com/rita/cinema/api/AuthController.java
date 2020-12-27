package com.rita.cinema.api;

import com.rita.cinema.domain.User;
import com.rita.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String login() {
        return "redirect:/films";
    }

    @GetMapping("register")
    public String registration(Model model) {
        model.addAttribute("isAuthenticated", false);
        model.addAttribute("isAdmin", false);
        return "registration";
    }

    @PostMapping("register")
    public String register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "birthDate") String birthDate,
                           Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date parsed = null;
        try {
            parsed = formatter.parse(birthDate);
        } catch (ParseException e) {
            model.addAttribute("dateMessage", "Please, enter correct birth date");
        }
        if(userService.isUnique(username) && parsed != null) {
            userService.add(username, password, name, email, parsed);
            return "redirect:/login";
        }
        model.addAttribute("isAuthenticated", false);
        model.addAttribute("isAdmin", false);
        model.addAttribute("usernameMessage", "Username must be unique");
        return "registration";
    }
}
