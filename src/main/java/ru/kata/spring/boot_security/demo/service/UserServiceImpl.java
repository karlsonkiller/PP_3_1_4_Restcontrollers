package ru.kata.spring.boot_security.demo.service;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repo.UserRepo;

import javax.management.relation.Role;
import java.beans.Transient;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    //@Lazy
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepo.getById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    @Transient
    public void saveUser(User user) {
        if(userRepo.findByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        } else {
         throw  new IllegalAccessError("User already exists");
        }
    }

    @Override
    @Transient
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(id);
        userRepo.save(user);
    }
}
