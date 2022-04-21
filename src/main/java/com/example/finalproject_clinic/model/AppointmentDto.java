package com.example.finalproject_clinic.model;

import com.example.finalproject_clinic.persistence.entity.Dentist;
import com.example.finalproject_clinic.persistence.entity.Patient;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDto {

    private Long id;
    private LocalDate dateAppointment;
    private Patient patient;
    private Dentist dentist;

}
