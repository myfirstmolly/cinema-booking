package com.rita.cinema.api;

import com.rita.cinema.domain.User;
import com.rita.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public User byId(@PathVariable String username, @AuthenticationPrincipal User user, Map<String, Object> model) {
        return userService.findByUsername(username);
    }

    @PostMapping
    public User register(@RequestParam(value = "username") String username,
                    @RequestParam(value = "password") String password,
                    @RequestParam(value = "name") String name,
                    @RequestParam(value = "email") String email,
                    @RequestParam(value = "birthDate") Date birthDate,
                    @AuthenticationPrincipal User user,
                    Map<String, Object> model) {
        User newUser = new User(username, password, name, email, birthDate);
        return userService.add(newUser);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal User user, Map<String, Object> model){
        userService.deleteById(id);
    }

    @PutMapping("{id}")
    public User updateName(@RequestParam(value = "name") String name,
                       @RequestParam(value = "summary", required = false) String summary,
                       @AuthenticationPrincipal User user,
                       Map<String, Object> model){
        return userService.updateName(user.getId(), summary);
    }
}
