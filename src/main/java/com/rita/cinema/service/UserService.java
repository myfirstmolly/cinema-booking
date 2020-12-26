package com.rita.cinema.service;

import com.rita.cinema.domain.Role;
import com.rita.cinema.domain.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    List<User> findAll();

    User add(User user);

    User add(String username, String password, String name, String email, Date birthDate);

    void deleteById(Long id);

    User changeRoles(Long id, List<Role> roles);

    boolean isUnique(String username);
}
