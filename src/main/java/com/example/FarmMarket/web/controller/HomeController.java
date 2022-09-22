package com.example.FarmMarket.web.controller;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getFirstPage() {
        return "home";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }
}

