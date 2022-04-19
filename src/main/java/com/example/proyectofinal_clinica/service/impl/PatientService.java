package com.example.proyectofinal_clinica.service.impl;

import com.example.proyectofinal_clinica.exceptions.ResourceNotFoundException;
import com.example.proyectofinal_clinica.model.PatientDto;
import com.example.proyectofinal_clinica.persistence.entity.Patient;
import com.example.proyectofinal_clinica.persistence.repository.IPatientRepository;
import com.example.proyectofinal_clinica.service.IPatientService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDto findById(@NotNull Long id) throws ResourceNotFoundException {
        if (patientRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        Patient patient = patientRepository.getById(id);
        return mapToDto(patient);
    }

    @Override
    public PatientDto save(@NotNull PatientDto patientDto) throws ResourceNotFoundException {
        if (patientDto == null) {
            throw new ResourceNotFoundException("Patient not created");
        }
        Patient patient = mapToEntity(patientDto);
        Patient patientSaved = patientRepository.save(patient);
        return mapToDto(patientSaved);
    }

    @Override
    public void deleteById( @NotNull Long id) throws ResourceNotFoundException {
        if (patientRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDto update(@NotNull PatientDto patientDto) throws ResourceNotFoundException {
        if (patientRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientDto.getId());
        }
        Patient patient = mapToEntity(patientDto);
        Patient patientUpdated = patientRepository.save(patient);
        return mapToDto(patientUpdated);
    }

    @Override
    public List<PatientDto> findAll() throws ResourceNotFoundException {
        if (patientRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Patients not found");
        }
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    //---- Mapper ----//
    private PatientDto mapToDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }

    private Patient mapToEntity(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }
}
