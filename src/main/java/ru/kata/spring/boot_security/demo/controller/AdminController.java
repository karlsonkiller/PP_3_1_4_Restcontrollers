package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServ;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Controller
public class AdminController {
    private UserServiceImpl userServiceImpl;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("admin", userServiceImpl.findAll());
        return "admin";
    }

    @GetMapping("/admin/creat_user")
    public String getCreateUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllUser());
        return "creat_user";
    }
    @PostMapping("/creat_user")
    public String createUser(@ModelAttribute("user") User user) {
        userServiceImpl.saveUser(user);
        user.setPassword(user.getPassword());
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/update_user")
    public String getWhatUpdateUser(@ModelAttribute("id") Long id, Model model) {
        model.addAttribute("user", userServiceImpl.getById(id));
        model.addAttribute("roles", roleService.getAllUser());
        return "update_user";
    }

    @PostMapping("/admin/{id}/update_user")
    public String getUpdateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        user.setPassword(user.getPassword());
        userServiceImpl.updateUser(id, user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }
}