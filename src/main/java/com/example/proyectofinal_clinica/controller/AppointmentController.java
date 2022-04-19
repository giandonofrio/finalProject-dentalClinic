package com.example.proyectofinal_clinica.controller;

import com.example.proyectofinal_clinica.exceptions.ResourceNotFoundException;
import com.example.proyectofinal_clinica.model.AppointmentDto;
import com.example.proyectofinal_clinica.service.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @GetMapping
    public ResponseEntity getAllAppointments() throws ResourceNotFoundException {
        List<AppointmentDto> appointmentDtoList = appointmentService.findAll();
        return ResponseEntity.ok("Appointments: " + appointmentDtoList);
    }

    @PostMapping
    public ResponseEntity createAppointment(@RequestBody AppointmentDto appointmentDto) throws ResourceNotFoundException {
        appointmentService.save(appointmentDto);
        return ResponseEntity.ok("Appointment created, date: " + appointmentDto.getDateAppointment());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        appointmentService.deleteById(id);
        return ResponseEntity.ok("Appointment deleted");
    }

}





