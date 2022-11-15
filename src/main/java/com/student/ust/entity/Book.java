package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Book.
 */
@Entity
@Data
@Table(name="book_ustbatch_mappedby")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int bookId;

    private String bookName;
    private String bookAuthorName;
    private int bookIsp;

    /**
     * The Student.
     */
    @ManyToOne
    @JoinColumn(name="student_id")
    Student student;


}
