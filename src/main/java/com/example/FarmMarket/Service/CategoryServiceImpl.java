package com.example.FarmMarket.Service;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByCategory(String category) {

        return null;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Iterable<Category> findAll() {
        Iterable<Category> categories = categoryRepository.findAll();
        return categories;
    }

}