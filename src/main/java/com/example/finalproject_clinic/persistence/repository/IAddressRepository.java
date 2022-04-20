package com.example.finalproject_clinic.persistence.repository;

import com.example.finalproject_clinic.persistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {

}
