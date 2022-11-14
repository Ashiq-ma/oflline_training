package com.student.ust.service;


import com.student.ust.Exception.InvalidEmail;
import com.student.ust.Exception.InvalidPassword;
import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import com.student.ust.util.utilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.student.ust.util.utilClass.getSHA;


/**
 * The type Student service.
 */
@Service
public class StudentService {

    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    public Student getStudentByID(int id) {

        Student studentById = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        // getByName(studentById.getName());
        return studentById;


    }

    private void getByName(String name) {
        Student studentByName = studentRepository.findByName(name);
        System.out.printf("Student name is   >>>>>>" + studentByName.getName());
        System.out.printf("Student roll number is >>>>>>>" + studentByName.getRollno());
    }

    /**
     * Save student.
     *
     * @param student the student
     */
    public void saveStudent(Student student) {
        student.setDate(LocalDate.now());
        student.setModifiedDate(LocalDate.now());
        student.setCurrTime(Time.valueOf(LocalTime.now()));
        String email=student.getEmail();
        String password=student.getPassword();
        int result = validateEmail(email);
        int result1=validatePassword(password);


        if(result==0 && result1==0) {
            student.setPassword(utilClass.toHexString(getSHA(password)));
            studentRepository.save(student);
        }
        else if(result==1){
            throw new InvalidEmail();
        }
        else {
            throw new InvalidPassword();
        }




    }

    /**
     * Delete student.
     *
     * @param id the id
     */
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);

    }

    /**
     * Gets student all.
     *
     * @return the student all
     */
    public List<Student> getStudentAll() {
        return studentRepository.findAll();
    }

    /**
     * Update student student.
     *
     * @param student the student
     * @return the student
     */
    public Student updateStudent(Student student) {
        Student updateStudent = studentRepository.findById(student.getStudentId()).orElseThrow(() -> new NoSuchElementException());
        updateStudent.setName(student.getName());
        updateStudent.setRollno(student.getRollno());
        updateStudent.setModifiedDate(LocalDate.now());
        studentRepository.save(updateStudent);
        return updateStudent;
    }


    /**
     * Validate email int.
     *
     * @param email the email
     * @return the int
     */
    public int validateEmail(String email) {
        String regex = "^([A-Za-z0-9+_.-]+)@([A-Za-z0-9]+)\\.([a-z]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() == true) {
            return 0;
        } else {
            throw new InvalidEmail();
        }

    }

    /**
     * Validate password int.
     *
     * @param password the password
     * @return the int
     */
    public int validatePassword(String password) {
        String regexPassword = "^(?=(?:.*\\d){3,})(?=\\S+$)(?=.*[@#$%^&+=])(?=(?:.*[A-Za-z]){3,}).{8,}$";
        Pattern pattern = Pattern.compile(regexPassword);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches() == true) {
            return 0;
        } else {
            throw new InvalidPassword();
        }
    }







}




