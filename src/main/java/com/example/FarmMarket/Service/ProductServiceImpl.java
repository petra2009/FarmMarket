package com.example.FarmMarket.Service;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.model.Product;
import com.example.FarmMarket.DAO.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return null;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    public Iterable<Product> findAll() {
        Iterable<Product> products = productRepository.findAll();
        return products;
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}