package com.example.springsecurityaccio.dao;

import com.example.springsecurityaccio.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {


    @Query("select s from Student s where s.username = ?1")
    Optional<Student> findByUsername(String username);
}
