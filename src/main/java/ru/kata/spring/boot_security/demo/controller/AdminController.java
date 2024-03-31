package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserServiceImpl userServiceImpl;
    private RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPage(Principal principal, Model model) {
        model.addAttribute("user", userServiceImpl.findUserByUsername(principal.getName()));
        model.addAttribute("roles", roleService.getAllUser());
        model.addAttribute("allUsers", userServiceImpl.findAll());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/create_user")
    public String getAddUser(@ModelAttribute("user") User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }


    @PatchMapping("/{id}/update_user")
    public String getUpdateUser(@ModelAttribute("user") User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}/delete_user")
    public String deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }
}