package com.example.FarmMarket.service;

import com.example.FarmMarket.DAO.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUserByUsername(String username);
    boolean save (User user);
}
