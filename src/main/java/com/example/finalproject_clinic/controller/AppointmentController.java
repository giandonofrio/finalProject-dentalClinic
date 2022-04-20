package com.example.finalproject_clinic.controller;

import com.example.finalproject_clinic.exceptions.BadRequestException;
import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.AppointmentDto;
import com.example.finalproject_clinic.service.impl.AppointmentService;
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
    public ResponseEntity createAppointment(@RequestBody AppointmentDto appointmentDto) throws ResourceNotFoundException, BadRequestException {
        AppointmentDto appointmentDtoCreated = appointmentService.save(appointmentDto);
        return ResponseEntity.ok("Appointment created, date: " + appointmentDtoCreated.getDateAppointment());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        appointmentService.deleteById(id);
        return ResponseEntity.ok("Appointment deleted");
    }

}





