package com.example.FarmMarket.service;

import com.example.FarmMarket.DAO.model.Category;

public interface CategoryService {

    void deleteById(int id);

    Category findByCategory(String category);

    void save(Category category);

    Iterable<Category> findAll();

}
