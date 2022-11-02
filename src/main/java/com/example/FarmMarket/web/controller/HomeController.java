package com.example.FarmMarket.web.controller;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.model.Product;
import com.example.FarmMarket.DAO.repository.CategoryRepository;
import com.example.FarmMarket.DAO.repository.ProductRepository;
import com.example.FarmMarket.Service.CategoryService;
import com.example.FarmMarket.Service.ProductService;
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
    private final CategoryService categoryService;
    private final ProductService productService;


    public HomeController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("")
    private String getAdminPage(Model productsModel, Model categoryModel) {
        Iterable<Category> categories = categoryService.findAll();
        Iterable<Product> products = productService.findAll();
        categoryModel.addAttribute("categories", categories);
        productsModel.addAttribute("products", products);
        return "home";
    }
    @GetMapping("/{category}")
    private String getFilter (@PathVariable("category") String category,
                              Model categoryModel,
                              Model productsModel)  {
        Iterable<Category> categories = categoryService.findAll();
        Category categoryId = categoryService.findByCategory(category);
        List<Product> products = productService.findAllByCategory(categoryId);
        categoryModel.addAttribute("categories", categories);
        productsModel.addAttribute("products", products);
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

