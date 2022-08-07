package ru.ineb.jpainheritance.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SECOND_SUBJECT")
@Data
public class SecondSubject extends Subjects {
    private int num2;
    private String str2;
}
