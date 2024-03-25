package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServ;

import java.security.Principal;


@Controller
public class UserController {
    private final UserServ userServ;

    @Autowired
    public UserController(UserServ userServ) {
        this.userServ = userServ;
    }


    @GetMapping("/user")
    public String showUserPage(Principal principal, Model model) {
        model.addAttribute("user", userServ.findByUsername(principal.getName()));
        return "user";
    }
}
