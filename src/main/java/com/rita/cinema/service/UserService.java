package com.rita.cinema.service;

import com.rita.cinema.domain.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User add(User user);
    User add(String username, String password, String name, String email, Date birthDate);
    User findByUsername(String username);
    User updateName(Long id, String newName);
    void deleteById(Long id);

}
