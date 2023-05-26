package com.example.crudProject;

import com.example.crudProject.model.Student;
import com.example.crudProject.repository.IStudentRepository;
import com.example.crudProject.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration
@SpringBootTest
public class StudentServiceTest {
    @Mock
    IStudentRepository studentRepository;
    StudentService studentService ;

    @BeforeEach
    public void setupTest(){
        studentRepository = mock(IStudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @AfterEach
    public void testDown() {
        studentService = null;
        studentRepository = null;
    }
    @Test
    public void Should_Get_Student(){
        //arrange

        Student student = new Student("111","aaaaa","kadin",true,"mezun");
        when(studentRepository.findAllByName(student.getName())).thenReturn((List.of(student)));
        //act

        List<Student> result = studentService.getStudent("aaaaa");
        //assert

        assertEquals(List.of(student),result);
    }

    @Test
    public void Throw_Exception_When_ParameterIsNull(){

        assertThrows(NullPointerException.class,()->{
                studentService.createStudent(null);
        });
    }

    @Test
    public void Should_Success_When_UpdateStudent() throws Exception {
        //arrange

        Student student = new Student("111","aaaaa","kadin",true,"mezun");
        student.setId("test-id");
        Student updatedStudent = (Student) student.clone();
        updatedStudent.setName("bbbbb");
        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById("test-id")).thenReturn(Optional.of(student));
        //act

        Student result = studentService.updateStudent("test-id",updatedStudent);
        //assert

        assertEquals(result.getName(),"bbbbb");
        assertEquals(result.getId(),"test-id");
    }

    @Test
    public void Should_ThrowException_When_UpdatedProductIdIsNull() throws CloneNotSupportedException {

        Student student = new Student("111", "aaaaa", "kadin", true, "mezun");
        student.setId("test-id");
        Student updatedStudent = (Student) student.clone();
        updatedStudent.setName("bbbbb");

        assertThrows(IllegalArgumentException.class,()->{
            studentService.updateStudent(null,student);
        });
    }
    @Test
    public void Should_Success_When_DeleteProduct() throws Exception {
        //arrange
        doNothing().when(studentRepository).deleteById("test-id");

        //act
        studentService.deleteStudent("test-id");

        //assert
        verify(studentRepository,times(1)).deleteById("test-id");
    }

    @Test
    public void Should_ThrowException_When_Delete_ProductIdIsNull() {

        assertThrows(IllegalArgumentException.class,()->{
            studentService.deleteStudent(null);
        });
        verify(studentRepository,times(0)).deleteById("test-id");
    }



    }
