package com.example.proyectofinal_clinica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dentists")
@EqualsAndHashCode
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_seq")
    @SequenceGenerator(name = "dentist_seq", sequenceName = "dentist_seq", allocationSize = 1)
    @Column(name = "dentist_id")
    private Long id;
    private String name;
    private String lastName;
    private String matricula;

    @OneToMany(mappedBy = "dentist",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> appointments;
}
