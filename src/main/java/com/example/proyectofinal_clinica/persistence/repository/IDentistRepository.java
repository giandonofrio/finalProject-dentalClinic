package com.example.proyectofinal_clinica.persistence.repository;

import com.example.proyectofinal_clinica.persistence.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDentistRepository extends JpaRepository<Dentist, Long> {
    @Query("SELECT d FROM Dentist d WHERE d.enrollment = ?1")
    Optional<Dentist> findByEnrollment(String enrollment);

    @Query("SELECT d FROM Dentist d WHERE d.name = ?1")
    Optional<Dentist> findByName(String name);
}
