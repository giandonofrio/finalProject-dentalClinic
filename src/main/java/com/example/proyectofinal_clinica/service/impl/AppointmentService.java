package com.example.proyectofinal_clinica.service.impl;

import com.example.proyectofinal_clinica.model.AppointmentDto;
import com.example.proyectofinal_clinica.persistence.entity.Appointment;
import com.example.proyectofinal_clinica.persistence.repository.IAppointmentRepository;
import com.example.proyectofinal_clinica.service.IAppointmentService;
import com.sun.istack.NotNull;
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
    private ModelMapper modelMapper;

    @Override
    public AppointmentDto findById(@NotNull Long id) {
        Appointment appointment = appointmentRepository.getById(id);
        return mapToDto(appointment);
    }

    @Override
    public AppointmentDto save(AppointmentDto appointmentDto) {
        Appointment appointment = mapToEntity(appointmentDto);
        Appointment appointmentSaved = appointmentRepository.save(appointment);
        return mapToDto(appointmentSaved);
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentDto update(AppointmentDto appointmentDto) {
        Appointment appointment = mapToEntity(appointmentDto);
        Appointment appointmentSaved = appointmentRepository.save(appointment);
        return mapToDto(appointmentSaved);
    }

    @Override
    public List<AppointmentDto> findAll() {
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
