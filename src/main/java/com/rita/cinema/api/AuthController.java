package com.rita.cinema.api;

import com.rita.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                           @RequestParam(value = "birthDate") String birthDate,
                           Map<String, Object> model) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date parsed = formatter.parse(birthDate);
        userService.add(username, password, name, email, parsed);
        return "redirect:/login";
    }
}
