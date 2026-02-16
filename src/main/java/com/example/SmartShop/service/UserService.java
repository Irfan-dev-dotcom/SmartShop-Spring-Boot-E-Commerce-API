package com.example.SmartShop.service;

import com.example.SmartShop.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return userRepository.findAll(pageable);
    }
    public List<User>getAllUsersList(){
        return userRepository.findAll();
    }
    public User getUserById(Long id) throws Throwable {
        return (User) userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    public String deleteUser(Long id) throws Throwable {
        User user = getUserById(id);
        userRepository.delete(user);
        return "User deleted successfully";
    }
}
