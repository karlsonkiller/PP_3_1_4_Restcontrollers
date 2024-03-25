package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepo;

import java.util.List;


@Service
public class RoleServiceImpl  implements RoleService{
    private RoleRepo roleRepo;

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAllUser() {
        return roleRepo.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepo.save(role);


    }
}
