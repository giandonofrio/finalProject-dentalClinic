package com.example.proyectofinal_clinica.service;

import com.example.proyectofinal_clinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICRUDService <T> {
    T findById(Long id) throws ResourceNotFoundException;
    T save ( T t ) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;
    T update(T t) throws ResourceNotFoundException;
    List<T> findAll() throws ResourceNotFoundException;
}


