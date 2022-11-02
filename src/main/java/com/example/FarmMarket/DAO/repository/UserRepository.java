package com.example.FarmMarket.DAO.repository;

import com.example.FarmMarket.DAO.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
User findUserByUsername(String username);
}
