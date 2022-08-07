package ru.ineb.jpainheritance.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "OBJECTS")
@Data
public class InheritanceObject {
    @Id
    private UUID id;
    private String owner;
}
