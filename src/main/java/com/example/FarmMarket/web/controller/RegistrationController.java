package com.example.FarmMarket.web.controller;

import com.example.FarmMarket.DAO.model.Role;
import com.example.FarmMarket.DAO.model.User;
import com.example.FarmMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/registerUser")
    public String getRegisterPage() {
        return "userRegistration";
    }


    @PostMapping("/userRegister")
    public String registerUser(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "userRegistration";
        } else {
            user.setRole(Role.USER);
            userService.save(user);
            System.out.println("Новый пользователь с логином "+user.getUsername()+" успешно зарегистрирован.");
            return "redirect:/home";
        }
    }

}
