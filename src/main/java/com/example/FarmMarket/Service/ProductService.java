package com.example.FarmMarket.service;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.model.Product;

import java.util.List;

public interface ProductService {

    void deleteById(int id);

    void save(Product product);

    Iterable<Product> findAll();

    List<Product> findAllByCategory(Category category);
}
