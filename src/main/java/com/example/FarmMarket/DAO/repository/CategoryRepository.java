package com.example.FarmMarket.DAO.repository;

import com.example.FarmMarket.DAO.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
