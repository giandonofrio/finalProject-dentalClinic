package com.example.finalproject_clinic.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDto {

    private Long id;
    private LocalDate dateAppointment;
    private PatientDto patientDto;
    private DentistDto dentistDto;

    public AppointmentDto(LocalDate dateAppointment, PatientDto patientDto, DentistDto dentistDto) {
        this.dateAppointment = dateAppointment;
        this.patientDto = patientDto;
        this.dentistDto = dentistDto;
    }

    public AppointmentDto() {
    }
}
