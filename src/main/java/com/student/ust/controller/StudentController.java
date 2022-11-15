package com.student.ust.controller;

import com.student.ust.Exception.BusinessException;
import com.student.ust.dto.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto>
    get(@PathVariable Integer id) {
        try {
            Student student = studentService.getStudentByID(id);
            return new ResponseEntity<StudentDto>(studentService.convertToDto(student), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/student")
    public ResponseEntity<Student>
    get3(@RequestParam(name="id")Integer id) {
        try {
            Student student = studentService.getStudentByID(id);

            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }
        @GetMapping("/students")
        public ResponseEntity<List<Student>> get2(){
            try {
                List<Student> studentList=studentService.getStudentAll();
                return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
            }
    }

    @PutMapping("/students")
    public void updateStudent(@RequestBody Student student)
    {
        studentService.updateStudent(student);

    }


    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable Integer id){
        studentService.deleteStudent(id);
    }

        @PostMapping("/students")
        public ResponseEntity<Student> add(@RequestBody Student student) {

        try {
            studentService.saveStudent(student);

            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch(BusinessException e){
            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
        }

        }








