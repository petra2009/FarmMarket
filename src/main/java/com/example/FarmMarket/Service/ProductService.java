package com.example.FarmMarket.Service;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void deleteById(int id);

    void save(Product product);

    Iterable<Product> findAll();

    List<Product> findAllByCategory(Category category);

    Product findProductById(int id);

    List<Product> addInBasket(int id);


}
