package com.example.proyectofinal_clinica.persistence.repository;

import com.example.proyectofinal_clinica.persistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.number = ?1")
    Address findByNumber(String number);

    @Query("SELECT d FROM Address d WHERE d.street = ?1")
    Address findByStreet(String street);


}
