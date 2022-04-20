package com.example.finalproject_clinic.controller;

import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.PatientDto;
import com.example.finalproject_clinic.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity getPatient(@PathVariable Long id) throws ResourceNotFoundException {
        patientService.findById(id);
        return ResponseEntity.ok("Patient found: " + patientService.findById(id).toString());
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody PatientDto patientDto) throws ResourceNotFoundException {
        patientDto.setAdmissionDate(LocalDate.now());
        PatientDto patientCreated = patientService.save(patientDto);
        return ResponseEntity.ok("Patient created: " + patientCreated.getLastName() + ", " + patientCreated.getName() + " with ID: " + patientCreated.getId());
    }

    @GetMapping
    public ResponseEntity getAllPatients() throws ResourceNotFoundException {
        List<PatientDto> patientDtoList = patientService.findAll();
        return ResponseEntity.ok("Patients: " + patientDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) throws ResourceNotFoundException {
        patientService.deleteById(id);
        return ResponseEntity.ok("Patient deleted");

    }

    @PutMapping("/{id}")
    public ResponseEntity updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto) throws ResourceNotFoundException {
        patientService.update(patientDto);
        return ResponseEntity.ok("Patient updated");
    }

}
