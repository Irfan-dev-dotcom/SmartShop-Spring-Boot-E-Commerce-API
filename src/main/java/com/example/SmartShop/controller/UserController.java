package com.example.SmartShop.controller;

import com.example.SmartShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return userService.getAllUsers(page, size);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws Throwable {
    return userService.getUserById(id);
    }
    @GetMapping("/{id}")
    public String deleteUser(@PathVariable Long id) throws Throwable {
        return userService.deleteUser(id);
    }
}
