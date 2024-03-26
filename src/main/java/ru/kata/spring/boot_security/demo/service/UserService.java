package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User getById(Long id);

    User findUserByUsername(String username);

    void saveUser(User user);

    void deleteUser(Long id);

    void updateUser(Long id, User user);


}

