package com.example.finalproject_clinic.model;

import lombok.Data;

@Data

public class DentistDto {

    private Long id;
    private String name;
    private String lastName;
    private String enrollment;

    public DentistDto(Long id) {
        this.id = id;
    }

    public DentistDto(String name, String lastName, String enrollment) {
        this.name = name;
        this.lastName = lastName;
        this.enrollment = enrollment;
    }

    public DentistDto() {
    }
}

