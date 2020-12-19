package com.rita.cinema.api;

import com.rita.cinema.domain.User;
import com.rita.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> all(Map<String, Object> model) {
        return userService.findAll();
    }

    @GetMapping("{username}")
    public User byId(@PathVariable String username, Map<String, Object> model) {
        return userService.findByUsername(username);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id, Map<String, Object> model){
        userService.deleteById(id);
    }

    @PutMapping("{id}")
    public User updateName(@PathVariable Long id, @RequestParam(value = "name") String name,
                       Map<String, Object> model){
        return userService.updateName(id, name);
    }
}
