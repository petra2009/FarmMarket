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


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public AdminController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

//  Сохранение категории
    @PostMapping("addCategory")
    private String addCategory(@RequestParam String category,
                               @RequestParam("file") MultipartFile file) throws IOException {
        Category tempCategory = new Category(category);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            tempCategory.setFilename(resultFilename);
        }
        categoryRepository.save(tempCategory);
        return "redirect:categories";
    }

    // Удаление категории
    @GetMapping("delCategory{id}")
    private String delCategory(@RequestParam("id") Integer id) {
        categoryRepository.deleteById(id);
        return "redirect:categories";
    }

    //Вывод списка категорий
    @GetMapping("categories")
    private String getAdminPageCategories(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin";
    }

    //Сохранение продукта
    @PostMapping("/addProduct")
    private String addProduct(@RequestParam String product,
                              @RequestParam String title,
                              @RequestParam BigDecimal price,
                              @RequestParam Category category,
                              @RequestParam("file") MultipartFile file) throws IOException {
        Product newProduct = new Product(product, title, price, category);
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();                  //создание директории для картинок
            }
            String uuidFile = UUID.randomUUID().toString();         //новое имя для картинок
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));   //загрузка картинок
            newProduct.setFilename(resultFilename);
        }
        productRepository.save(newProduct);
        return "redirect:products";
    }

    //Вывод списка продуктов и категорий для формы заполнения
    @GetMapping("products")
    private String getAdminPageProducts(Model modelProducts, Model modelCategorySelect) {
        Iterable<Product> products = productRepository.findAll();
        modelProducts.addAttribute("products", products);
        Iterable<Category> categorySelect = categoryRepository.findAll();
        modelCategorySelect.addAttribute("categorySelect", categorySelect);
        return "admin";
    }

    // Удаление продукта
    @GetMapping("delProduct{id}")
    private String delProduct(@RequestParam("id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:products";
    }

}
