package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUsers(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editUsers(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
