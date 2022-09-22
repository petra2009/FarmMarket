package com.example.FarmMarket.DAO.repository;

import com.example.FarmMarket.DAO.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {


}
