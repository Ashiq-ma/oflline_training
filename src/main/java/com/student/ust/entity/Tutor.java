package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tutorId;

    private String tutorName;








}
