package com.example.proyectofinal_clinica.service;

import java.util.List;

public interface ICRUDService <T> {
    T findById(Long id);
    T save ( T t );
    void deleteById(Long id);
    T update(T t);
    List<T> findAll();
}


