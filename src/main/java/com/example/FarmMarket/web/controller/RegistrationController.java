package com.example.FarmMarket.web.controller;

import com.example.FarmMarket.DAO.model.Role;
import com.example.FarmMarket.DAO.model.User;
import com.example.FarmMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;


@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User getEmptyUser() {
        return new User();
    }

    @GetMapping("/userRegistration")
    public String getRegisterPage(Model model) {
        model.addAttribute("userForm", new User());
        return "userRegistration";
    }

    @PostMapping("/userRegister")
    public String registerUser(@Valid User user, Errors error) {
        if (error.hasErrors()) {
            return "userRegistration";
        } else if (!userService.save(user)) {
            return "userRegistration";
        } else {
            return "redirect:/home";
        }
    }
}
