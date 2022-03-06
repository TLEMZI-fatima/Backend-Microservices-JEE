package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.AppRole;
import com.example.demo.entity.AppUser;




public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
