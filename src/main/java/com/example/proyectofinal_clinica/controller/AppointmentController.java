package com.example.proyectofinal_clinica.controller;

import com.example.proyectofinal_clinica.model.AppointmentDto;
import com.example.proyectofinal_clinica.model.DentistDto;
import com.example.proyectofinal_clinica.model.PatientDto;
import com.example.proyectofinal_clinica.service.impl.AppointmentService;
import com.example.proyectofinal_clinica.service.impl.DentistService;
import com.example.proyectofinal_clinica.service.impl.PatientService;
import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DentistService dentistService;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDto appointmentDto) {

        PatientDto patientDto = patientService.findById(appointmentDto.getPatient().getId());
        DentistDto dentistDto = dentistService.findById(appointmentDto.getDentist().getId());

        if (patientDto != null && dentistDto != null) {
            appointmentService.save(appointmentDto);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        ResponseEntity<Void> responseEntity;
        AppointmentDto appointmentDto = appointmentService.findById(id);
        if (appointmentDto != null) {
            appointmentService.deleteById(id);
            responseEntity = ResponseEntity.ok().build();
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return responseEntity;
    }

}





