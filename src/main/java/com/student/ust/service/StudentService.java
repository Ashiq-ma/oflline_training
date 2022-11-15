package com.student.ust.service;


import com.student.ust.Exception.InvalidEmail;
import com.student.ust.Exception.InvalidPassword;
import com.student.ust.dto.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import com.student.ust.util.utilClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

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
    @Autowired
   private ModelMapper modelMapper;

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
        int result = utilClass.validateEmail(email);
        int result1=utilClass.validatePassword(password);


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

    /**
     * Validate password int.
     *
     * @param password the password
     * @return the int
     */



    /**
     * Convert to dto student dto.
     *
     * @param student the student
     * @return the student dto
     */
    public StudentDto convertToDto(Student student) {
        return modelMapper.map(student,StudentDto.class);
    }
}




