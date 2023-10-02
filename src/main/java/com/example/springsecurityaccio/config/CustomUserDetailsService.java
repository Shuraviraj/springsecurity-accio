package com.example.springsecurityaccio.config;

import com.example.springsecurityaccio.dao.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var student = studentRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid User"));

        return new UserDetailCreator(student);
    }
}
