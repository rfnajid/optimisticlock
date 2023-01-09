package com.example.optimisticlock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.optimisticlock.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    
}
