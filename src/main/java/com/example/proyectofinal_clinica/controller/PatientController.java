package com.example.proyectofinal_clinica.controller;

import com.example.proyectofinal_clinica.model.PatientDto;
import com.example.proyectofinal_clinica.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable("id") Long id) {
        if (patientService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(patientService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.save(patientDto));
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patientDtoList = patientService.findAll();
        if (patientDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable("id") Long id) {
        if (patientService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        patientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable("id") Long id, @RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.save(patientDto));
    }
}
