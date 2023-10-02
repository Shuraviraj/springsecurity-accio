package com.example.springsecurityaccio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
    /*    UserDetails student = User
                .withUsername("ravi")
                .password(getBCryptPasswordEncoder().encode("ravi"))
                .roles("STUDENT")
                .build();

        UserDetails admin = User
                .withUsername("bharti")
                .password(getBCryptPasswordEncoder().encode("bharti"))
                .roles("ADMIN")
                .build();*/
        return new CustomUserDetailsService();
    }


    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/public/**")
                .permitAll()
                .requestMatchers("/student/add/**")
                .permitAll()
                .requestMatchers("/student/**")
                .hasAnyRole("STUDENT", "ADMIN")
                .requestMatchers("/admin/add/**")
                .permitAll()
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        return daoAuthenticationProvider;
    }
}
