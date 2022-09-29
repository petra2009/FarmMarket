package com.example.FarmMarket.web.controller;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private CategoryRepository categoryRepository;

   /* @GetMapping("/")
    public String getFirstPage() {
        return "home";
    }*/

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping
    private String getAdminPage(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "home";
    }
}

