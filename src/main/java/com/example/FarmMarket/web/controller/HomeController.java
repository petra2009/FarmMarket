package com.example.FarmMarket.web.controller;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.model.Product;
import com.example.FarmMarket.DAO.repository.CategoryRepository;
import com.example.FarmMarket.DAO.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;


    public HomeController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    /* @GetMapping("/")
    public String getFirstPage() {
        return "home";
    }*/

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("")
    private String getAdminPage(Model productsModel, Model categoryModel) {
        Iterable<Category> categories = categoryRepository.findAll();
        Iterable<Product> products = productRepository.findAll();
        categoryModel.addAttribute("categories", categories);
        productsModel.addAttribute("products", products);
        return "home";
    }
    @GetMapping("/{category}")
    private String getFilter (@PathVariable("category") String category,
                              Model categoryModel,
                              Model productsmodel)  {
        Iterable<Category> categories = categoryRepository.findAll();
        Category categoryId = categoryRepository.findByCategory(category);
        List<Product> products = productRepository.findAllByCategory(categoryId);
        categoryModel.addAttribute("categories", categories);
        productsmodel.addAttribute("products", products);
        return "home";
    }

/*    @PostMapping("serch")
    private String getSerch (@RequestParam Integer serch,
                             Model productsModel,
                             Model categoryModel) {
        Optional<Category> categories = categoryRepository.findById(serch);

        categoryModel.addAttribute("categories", categories);

        return "home";
    }*/
}

