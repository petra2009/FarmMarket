package com.example.FarmMarket.service;

import com.example.FarmMarket.DAO.model.Role;
import com.example.FarmMarket.DAO.model.User;
import com.example.FarmMarket.DAO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        Set<Role> roles = new HashSet<>();
        if (user == null){
            throw new UsernameNotFoundException("Пользователь с логином " + username + " не существует.");
        } else {
            System.out.println("Пользователь с логином " + username + " залогинился.");
        }
        roles.add(user.getRole());
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), roles);
    }
}
