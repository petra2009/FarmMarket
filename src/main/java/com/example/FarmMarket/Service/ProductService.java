package com.example.FarmMarket.Service;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllByCategory(Category category);

    void save(Product product);

    Iterable<Product> findAll();

    void deleteById(int id);
}
