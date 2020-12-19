package com.rita.cinema.api;

import com.rita.cinema.domain.Role;
import com.rita.cinema.domain.User;
import com.rita.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<User> all(@AuthenticationPrincipal User user, Map<String, Object> model) {
        return userService.findAll();
    }

    @GetMapping("my-profile")
    public User oneUser(@AuthenticationPrincipal User user, Map<String, Object> model) {
        return user;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{username}")
    public User byId(@PathVariable String username,
                     @AuthenticationPrincipal User user,
                     Map<String, Object> model) {
        return userService.findByUsername(username);
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal User user,
                       Map<String, Object> model){
        userService.deleteById(user.getId());
    }

    @PutMapping("upd-name")
    public User updateName(@AuthenticationPrincipal User user,
                           @RequestParam(value = "name") String name,
                           Map<String, Object> model){
        return userService.updateName(user.getId(), name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}/add-role")
    public User addRole(@PathVariable Long id,
                        @RequestParam(value = "role") Role role,
                        @AuthenticationPrincipal User user,
                        Map<String, Object> model){
        return userService.addRole(id, role);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}/reset-role")
    public User resetRole(@PathVariable Long id,
                          @RequestParam(value = "role") Role role,
                          @AuthenticationPrincipal User user,
                          Map<String, Object> model){
        return userService.resetRole(id, role);
    }
}
