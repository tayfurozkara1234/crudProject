package com.example.crudProject.repository;

import com.example.crudProject.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;


public interface IStudentRepository extends MongoRepository<Student, String> {
    List<Student> findAllByName(String name);
    Optional<Student> findByName(String name);

}
