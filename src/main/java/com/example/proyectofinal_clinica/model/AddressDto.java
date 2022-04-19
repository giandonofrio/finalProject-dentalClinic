package com.example.proyectofinal_clinica.model;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String street;
    private String number;
    private String location;
    private String province;


    public AddressDto(String street, String number, String location, String province) {
        this.street = street;
        this.number = number;
        this.location = location;
        this.province = province;
    }

    public AddressDto() {
    }
}
