package com.example.proyectofinal_clinica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "dentists")
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_seq")
    @SequenceGenerator(name = "dentist_seq", sequenceName = "dentist_seq", allocationSize = 1)
    @Column(name = "dentist_id")
    private Long id;
    private String name;
    private String lastName;
    private String enrollment;

    @OneToMany(mappedBy = "dentist",fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<Appointment> appointments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dentist dentist = (Dentist) o;
        return id != null && Objects.equals(id, dentist.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
