package com.rita.cinema.api;

import com.rita.cinema.domain.Role;
import com.rita.cinema.domain.User;
import com.rita.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String all(Model model) {
        model.addAttribute("isAuthenticated", true);
        model.addAttribute("isAdmin", true);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("{user}")
    public String byId(@PathVariable User user, Model model) {
        model.addAttribute("isAuthenticated", true);
        model.addAttribute("isAdmin", true);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("{id}/roles")
    public String changeRoles(@PathVariable Long id,
                              @RequestParam(required = false) boolean isAdmin,
                              @RequestParam(required = false) boolean isUser){
        List<Role> roles = new ArrayList<>();
        if(isAdmin)
            roles.add(Role.ADMIN);
        if(isUser)
            roles.add(Role.USER);
        System.out.println(roles);
        userService.changeRoles(id, roles);
        return "redirect:/users";
    }
}
