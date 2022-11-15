package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

/**
 * The type Student.
 */
@Entity
@Data
@Table(name="student_ustbatch_mappedby")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int studentId;

    private String name;
    private int rollno;

    @NotNull
     private String email;

    @NotNull
    private String password;

    @OneToOne
    @JoinColumn(name="book_id")
    private Book book;

    private LocalDate date;
    private LocalDate modifiedDate;

    private Time currTime;

    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy = "student")
    private Set<Book> bookSet;



}
