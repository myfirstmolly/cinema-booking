package com.rita.cinema.service.impl;

import com.rita.cinema.domain.Role;
import com.rita.cinema.domain.User;
import com.rita.cinema.repository.UserRepository;
import com.rita.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(String username, String password, String name, String email, Date birthDate) {
        User user = new User(username, passwordEncoder.encode(password), name, email, birthDate);
        return userRepository.save(user);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User changeRoles(Long id, List<Role> roles) {
        User user = userRepository.findById(id).get();
        user.getRoles().clear();
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public boolean isUnique(String username) {
        return userRepository.findByUsername(username) == null;
    }
}
