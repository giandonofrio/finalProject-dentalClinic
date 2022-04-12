package com.example.proyectofinal_clinica.service.impl;

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
    public DentistDto findById(@NotNull Long id) {
        Dentist dentist = dentistRepository.getById(id);
        return mapToDto(dentist);
    }

    @Override
    public DentistDto save(@NotNull DentistDto dentistDto) {
        Dentist dentist = mapToEntity(dentistDto);
        Dentist dentistSaved = dentistRepository.save(dentist);
        return mapToDto(dentistSaved);
    }

    @Override
    public void deleteById(Long id) {
        Dentist dentist = dentistRepository.getById(id);
        dentistRepository.delete(dentist);
    }

    @Override
    public DentistDto update(DentistDto dentistDto) {
        Dentist dentist = mapToEntity(dentistDto);
        Dentist dentistSaved = dentistRepository.save(dentist);
        return mapToDto(dentistSaved);
    }

    @Override
    public List<DentistDto> findAll() {
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
