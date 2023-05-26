package com.example.crudProject.service;


import com.example.crudProject.model.Student;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.crudProject.repository.IStudentRepository;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private IStudentRepository studentRepository;

    public List<Student> getStudent(String name) {
        if (name == null) {
            return studentRepository.findAll();
        } else {
            return studentRepository.findAllByName(name);
        }
    }

    public Student createStudent(Student newStudent) {
        Optional<Student> StudentByName = studentRepository.findByName(newStudent.getName());

        if(newStudent == null){
            throw new IllegalArgumentException("productItem can not be null");
        }
        return studentRepository.save(newStudent);
    }

    public void deleteStudent(String id) {
        if(id == null){
            throw new IllegalArgumentException("deletedProductId can not be empty");
        }
        studentRepository.deleteById(id);
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new FileSystemNotFoundException("Il not found with id: " + id));
    }

    public Student updateStudent(String id, Student newStudent) {

        if(id == null){
            throw new IllegalArgumentException("newProductItem can not be null");
        }
        studentRepository.save(newStudent);
        return newStudent;
    }


}
