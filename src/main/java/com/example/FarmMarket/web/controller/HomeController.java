package com.example.FarmMarket.web.controller;

import org.springframework.stereotype.Controller;
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

