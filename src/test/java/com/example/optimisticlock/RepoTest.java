package com.example.optimisticlock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.OptimisticLockingFailureException;

import com.example.optimisticlock.entity.Student;
import com.example.optimisticlock.repository.StudentRepository;


@DataJpaTest
public class RepoTest {

    @Autowired
    StudentRepository repo;

    @Test
    void testOptimisticLock(){
        Student initStudent = new Student(1L, "Andy", 0L);
        Student insertedStudent = repo.saveAndFlush(initStudent);

        assertEquals(0, insertedStudent.getVersion());

        insertedStudent.setName("Jack");
        Student updatedStudent = repo.saveAndFlush(insertedStudent);

        assertEquals(1, updatedStudent.getVersion());

        assertThrows(OptimisticLockingFailureException.class, () ->
            repo.saveAndFlush(initStudent)
        );
    }
}
