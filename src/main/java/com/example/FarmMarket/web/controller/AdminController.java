package com.example.FarmMarket.web.controller;

import com.example.FarmMarket.DAO.model.Category;
import com.example.FarmMarket.DAO.model.Product;
import com.example.FarmMarket.DAO.repository.CategoryRepository;
import com.example.FarmMarket.DAO.repository.ProductRepository;
import com.example.FarmMarket.service.CategoryService;
import com.example.FarmMarket.service.CategoryServiceImpl;
import com.example.FarmMarket.service.ProductService;
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
    private final CategoryService categoryService;
    private final ProductService productService;


    public AdminController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    //  Сохранение категории
    @PostMapping("addCategory")
    private String addCategory(@RequestParam String category,
                               @RequestParam("file") MultipartFile file) throws IOException {
        Category newCategory = new Category(category);
        if (file != null) {     // проверяет добавлена ли картинка
            File uploadDir = new File(uploadPath);  // проверяет есть ли папка
            if (!uploadDir.exists()) {
                uploadDir.mkdir();      //если нет то создание директории для картинок
            }
            String uuidFile = UUID.randomUUID().toString();     //генерирует произвольной суффикс
            String resultFilename = uuidFile + "." + file.getOriginalFilename();  // добавляет новый суффикс к имени файла
            file.transferTo(new File(uploadPath + "/" + resultFilename)); //загрузка картинок в папку указаную в upload.path
            newCategory.setFilename(resultFilename); // сохраняет новое имя файла в БД
        }
        categoryService.save(newCategory); // сохраняет категорию в БД
        return "redirect:categories";
    }

    // Удаление категории
    @GetMapping("delCategory{id}")
    private String delCategory(@RequestParam("id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:categories";
    }

    //Вывод списка категорий
    @GetMapping("categories")
    private String getAdminPageCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
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
        productService.save(newProduct);
        return "redirect:products";
    }

    //Вывод списка продуктов и категорий для формы заполнения
    @GetMapping("products")
    private String getAdminPageProducts(Model modelProducts, Model modelCategorySelect) {
        modelProducts.addAttribute("products", productService.findAll());
        modelCategorySelect.addAttribute("categorySelect", categoryService.findAll());
        return "admin";
    }

    // Удаление продукта
    @GetMapping("delProduct{id}")
    private String delProduct(@RequestParam("id") Integer id) {
        productService.deleteById(id);
        return "redirect:products";
    }

}
