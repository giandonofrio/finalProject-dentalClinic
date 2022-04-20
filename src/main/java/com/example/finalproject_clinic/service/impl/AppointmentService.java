package com.example.finalproject_clinic.service.impl;

import com.example.finalproject_clinic.exceptions.BadRequestException;
import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.AppointmentDto;
import com.example.finalproject_clinic.model.DentistDto;
import com.example.finalproject_clinic.model.PatientDto;
import com.example.finalproject_clinic.persistence.entity.Appointment;
import com.example.finalproject_clinic.persistence.repository.IAppointmentRepository;
import com.example.finalproject_clinic.service.IAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {


    @Autowired
    private IAppointmentRepository appointmentRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DentistService dentistService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppointmentDto findById(Long id) throws ResourceNotFoundException {
        if (appointmentRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        Appointment appointment = appointmentRepository.getById(id);
        return mapToDto(appointment);
    }

    @Override
    public AppointmentDto save(AppointmentDto appointmentDto) throws ResourceNotFoundException, BadRequestException {

        if (appointmentDto.getPatientDto().getId() == null || appointmentDto.getDentistDto().getId() == null) {
            PatientDto patientDto = patientService.findById(appointmentDto.getPatientDto().getId());
            DentistDto dentistDto = dentistService.findById(appointmentDto.getDentistDto().getId());
            throw new ResourceNotFoundException("Patient or Dentist not found with id");
        }

        Appointment appointment = mapToEntity(appointmentDto);
        Appointment appointmentSaved = appointmentRepository.save(appointment);
        return mapToDto(appointmentSaved);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (appointmentRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }

        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentDto update(AppointmentDto appointmentDto) throws ResourceNotFoundException {
        if (appointmentRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Appointment not found with id: " + appointmentDto.getId());
        }
        Appointment appointment = mapToEntity(appointmentDto);
        Appointment appointmentSaved = appointmentRepository.save(appointment);
        return mapToDto(appointmentSaved);
    }

    @Override
    public List<AppointmentDto> findAll() throws ResourceNotFoundException {
        if (appointmentRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Appointment not found");
        }
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    //---- Mapper ----//
    private AppointmentDto mapToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    private Appointment mapToEntity(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }


}
