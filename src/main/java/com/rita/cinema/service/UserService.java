package com.rita.cinema.service;

import com.rita.cinema.domain.User;

public interface UserService {
    User add(User user);
    User findByUsername(String username);
    User updateName(Long id, String newName);
    void deleteById(Long id);
}
