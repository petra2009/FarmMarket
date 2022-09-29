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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private CategoryRepository categoryRepository;
//    private ProductRepository productRepository;
/*

    public AdminController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
*/

//  Ввод категории и продукта

    @PostMapping("addCategory")
    private String addCategory(@RequestParam String category,
                               @RequestParam("file") MultipartFile file) throws IOException {
        Category category1 = new Category(category);

        if (file != null) {

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            category1.setFilename(resultFilename);
        }
        categoryRepository.save(category1);
        return "redirect:categories";
    }

    @GetMapping("delCategory{id}")
    private String delCategory(@RequestParam("id") int id) {
        categoryRepository.deleteById(id);
        return "redirect:categories";
    }

    @GetMapping("categories")
    private String getAdminPage(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin";
    }
/*

    @PostMapping("/addProduct")
    private String addProduct(@RequestParam String product,
                               @RequestParam BigDecimal price) {
        Product product1 = new Product(product, price);
        productRepository.save(product1);
        return "admin";
    }
*/




}
