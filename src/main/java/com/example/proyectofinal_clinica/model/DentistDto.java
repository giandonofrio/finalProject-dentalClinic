package com.example.proyectofinal_clinica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentistDto {

    private Long id;
    private String name;
    private String lastName;
    private String matricula;


}
