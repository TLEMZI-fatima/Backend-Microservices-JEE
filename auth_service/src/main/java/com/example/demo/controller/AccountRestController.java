package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.AppRole;
import com.example.demo.entity.AppUser;
import com.example.demo.services.AccountService;
import com.example.demo.utis.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;


@RestController
@RequestMapping("/login")

public class AccountRestController {
	@Autowired

    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('USER')")
    public List<AppUser> appUsers() {
        return accountService.listUsers();
    }
    @PostMapping(path = " ")
    // @PostAuthorize("hasAuthority('ADMIN')")
    public List<AppUser> apUsers() {
        return accountService.listUsers();
    }
    
    @PostMapping(path = "/register")
    // @PostAuthorize("hasAuthority('ADMIN')")
     public AppUser saveUse(@RequestBody AppUser appUser) {
         return accountService.addNewUser(appUser);
     }

    @PostMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser(@RequestBody AppUser appUser) {
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = "/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppRole saveRole(@RequestBody AppRole appRole) {
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

    @GetMapping(path = "refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationToken = request.getHeader(JWTUtils.AUTH_HEADER);
        if(authorizationToken != null && authorizationToken.startsWith(JWTUtils.PREFIX)) {
            try {
                // verfiy the signature with the secret key
                String jwtRefreshToken = authorizationToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JWTUtils.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);

                // if it's valid, proceed
                String username = decodedJWT.getSubject();

                // verify if the user token exists in the BLACK-LIST
                AppUser appUser = accountService.loadUserByUsername(username);

                // ACCESS TOKEN
                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtils.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getAppRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);

                // TokenId = refresh-token + access-token
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", jwtRefreshToken);
                response.setContentType("application/json");

                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch(Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh Token Required !");
        }
    }

    @GetMapping(path = "profile")
    public AppUser profile(Principal principal) {
        return accountService.loadUserByUsername(principal.getName());
    }
}

@Data
class RoleUserForm {
    private String username;
    private String roleName;
}
