package com.example.crudProject.controller;

import com.example.crudProject.model.Student;
import com.example.crudProject.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/students")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    private Student getStudentById(String id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudentsAll(@RequestParam(required = false) String name){
        return new ResponseEntity<>(studentService.getStudent(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        return new ResponseEntity<>(getStudentById(id), OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {
        return new ResponseEntity<>(studentService.createStudent(newStudent), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student newStudent) {
        studentService.updateStudent(id, newStudent);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(OK);
    }




}
