package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.AppRole;
import com.example.demo.entity.AppUser;
import com.example.demo.services.AccountService;



@SpringBootApplication
//to control roles access method 2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AuthServiceApplication {

 public static void main(String[] args) {
     SpringApplication.run(AuthServiceApplication.class, args);
 }

 @Bean
 PasswordEncoder passwordEncoder() {
     // We are initializing PasswordEncoder bean in app context here to solve the circular dependency problem
     // if we defined it in SecurityConfig It will produce the problem
     return new BCryptPasswordEncoder();
 }

 @Bean
 CommandLineRunner  start(AccountService accountService) {
     return args -> {
         // ROLES
         accountService.addNewRole(new AppRole(null, "USER"));
         accountService.addNewRole(new AppRole(null, "ADMIN"));
         accountService.addNewRole(new AppRole(null, "CUSTOMER_MANAGER"));
         accountService.addNewRole(new AppRole(null, "PRODUCT_MANAGER"));
         accountService.addNewRole(new AppRole(null, "BILLS_MANAGER"));

         // USERS
         accountService.addNewUser(new AppUser(null, "user1", "amal@gmail.com", "1234", new ArrayList<>()));
         accountService.addNewUser(new AppUser(null, "admin",  "fati@gmail.com", "1234", new ArrayList<>()));
         accountService.addNewUser(new AppUser(null, "user2",  "oma@gmail.com", "1234", new ArrayList<>()));
         accountService.addNewUser(new AppUser(null, "user3", "maua@gmail.com", "1234", new ArrayList<>()));
         accountService.addNewUser(new AppUser(null, "user4", "1234", "mlk@gmail.com", new ArrayList<>()));

         // MAPPING ROLE-USER
         accountService.addRoleToUser("user1", "USER");
         accountService.addRoleToUser("admin", "USER");
         accountService.addRoleToUser("admin", "ADMIN");
         accountService.addRoleToUser("user2", "USER");
         accountService.addRoleToUser("user2", "CUSTOMER_MANAGER");
         accountService.addRoleToUser("user3", "USER");
         accountService.addRoleToUser("user3", "PRODUCT_MANAGER");
         accountService.addRoleToUser("user4", "USER");
         accountService.addRoleToUser("user4", "BILLS_MANAGER");

     };
 }
}
