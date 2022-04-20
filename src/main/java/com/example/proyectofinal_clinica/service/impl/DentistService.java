package com.example.proyectofinal_clinica.service.impl;

import com.example.proyectofinal_clinica.exceptions.ResourceNotFoundException;
import com.example.proyectofinal_clinica.model.DentistDto;
import com.example.proyectofinal_clinica.persistence.entity.Dentist;
import com.example.proyectofinal_clinica.persistence.repository.IDentistRepository;
import com.example.proyectofinal_clinica.service.IDentistService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentistService implements IDentistService {

    @Autowired
    private IDentistRepository dentistRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DentistDto findById(Long id) throws ResourceNotFoundException {
        if (dentistRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Dentist not found with id:" + id);
        }
        Dentist dentist = dentistRepository.getById(id);
        return mapToDto(dentist);
    }

    @Override
    public DentistDto save(DentistDto dentistDto) throws ResourceNotFoundException {
        if (dentistDto == null) {
            throw new ResourceNotFoundException("Dentist not created");
        }

        Dentist dentist = mapToEntity(dentistDto);
        return mapToDto(dentistRepository.save(dentist));
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (dentistRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Dentist not found with id:" + id);
        }
        dentistRepository.deleteById(id);
    }

    @Override
    public DentistDto update(DentistDto dentistDto) throws ResourceNotFoundException {
        if (dentistRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Dentist not found with id:" + dentistDto.getId());
        }

        Dentist dentist = mapToEntity(dentistDto);
        Dentist dentistSaved = dentistRepository.save(dentist);
        return mapToDto(dentistSaved);
    }

    @Override
    public List<DentistDto> findAll() throws ResourceNotFoundException {
        if (dentistRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Dentists not found");
        }
        List<Dentist> dentistList = dentistRepository.findAll();
        return dentistList.stream().map(this::mapToDto).collect(Collectors.toList());
    }


    //---- Mapper ----//
    private DentistDto mapToDto(Dentist dentist) {
        return modelMapper.map(dentist, DentistDto.class);
    }

    private Dentist mapToEntity(DentistDto dentistDto) {
        return modelMapper.map(dentistDto, Dentist.class);
    }


}
