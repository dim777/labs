package ru.ineb.jpainheritance.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FIRST_SUBJECT")
@Data
public class FirstSubject extends Subjects {
    private int num1;
    private String str1;
}
