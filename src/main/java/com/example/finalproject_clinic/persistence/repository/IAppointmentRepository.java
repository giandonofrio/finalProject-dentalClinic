package com.example.finalproject_clinic.persistence.repository;

import com.example.finalproject_clinic.persistence.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}

