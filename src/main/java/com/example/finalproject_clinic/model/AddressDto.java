package com.example.finalproject_clinic.model;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String street;
    private String number;
    private String location;
    private String province;

}
