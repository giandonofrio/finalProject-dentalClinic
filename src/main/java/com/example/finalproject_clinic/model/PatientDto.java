package com.example.finalproject_clinic.model;

import lombok.*;

import java.time.LocalDate;

@Data
public class PatientDto {

    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private LocalDate admissionDate;
    private AddressDto address;




}
