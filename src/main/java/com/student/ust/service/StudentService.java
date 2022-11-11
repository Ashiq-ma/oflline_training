package com.student.ust.service;


import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

@Autowired
    StudentRepository studentRepository;
    public Student getStudentByID(int id) {

        Student studentById=studentRepository.findById(id).orElseThrow(()->new NoSuchElementException());
       // getByName(studentById.getName());
        return studentById;


    }

    private void getByName(String name) {
        Student studentByName=studentRepository.findByName(name);
        System.out.printf("Student name is   >>>>>>"+studentByName.getName());
        System.out.printf("Student roll number is >>>>>>>"+studentByName.getRollno());
    }

    public void saveStudent(Student student) {
        student.setDate(LocalDate.now());
        student.setModifiedDate(LocalDate.now());
        student.setCurrTime(Time.valueOf(LocalTime.now()));
            studentRepository.save(student);


    }
    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);

    }
    public List<Student> getStudentAll(){
        return studentRepository.findAll();
    }
    public Student updateStudent(Student student){
        Student updateStudent=studentRepository.findById(student.getStudentId()).orElseThrow(()->new NoSuchElementException());
        updateStudent.setName(student.getName());
        updateStudent.setRollno(student.getRollno());
        updateStudent.setModifiedDate(LocalDate.now());
        studentRepository.save(updateStudent);
        return updateStudent;
    }



}


