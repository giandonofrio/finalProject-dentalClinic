package com.example.proyectofinal_clinica.persistence.repository;

import com.example.proyectofinal_clinica.persistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAddressRepository extends JpaRepository<Address, Long> {

}
