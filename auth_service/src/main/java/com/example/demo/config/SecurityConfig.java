package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filters.JwtAuthenticationFilter;
import com.example.demo.filters.JwtAuthorizationFilter;
import com.example.demo.security.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // by default spring security protect against CSRF attack
        // when dealing with stateful authentication we leave it enabled
        // when dealing with stateless authentication we leave it disabled
        http.csrf().disable();
        // use statless auth, do not use sessions
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // by default h2 database uses frames in HTML which they are vulnerable,
        // spring-security disables it by default
        http.headers().frameOptions().disable();
//        http.formLogin();
        http.authorizeRequests().antMatchers("/h2-console/**", "/login/**", "/refreshToken/**").permitAll();

        // to control roles access method 1
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAuthority("USER");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        // when we define multiple filter we need to specify which one runs first
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

