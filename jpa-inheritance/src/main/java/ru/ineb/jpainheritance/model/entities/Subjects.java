package ru.ineb.jpainheritance.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "SUBJECTS")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Subjects {
    @Id
    private UUID id;
    private String name;
}
