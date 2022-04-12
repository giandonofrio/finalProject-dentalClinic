package com.example.proyectofinal_clinica.persistence.repository;

import com.example.proyectofinal_clinica.persistence.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}

