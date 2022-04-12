package com.example.proyectofinal_clinica.controller;

import com.example.proyectofinal_clinica.model.DentistDto;
import com.example.proyectofinal_clinica.service.impl.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping
    public ResponseEntity<List<DentistDto>> getAllDentists() {
        List<DentistDto> dentists = dentistService.findAll();
        if (dentists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dentists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistDto> getDentist(@PathVariable Long id) {
        if (dentistService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(dentistService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DentistDto> createDentist(@RequestBody DentistDto dentistDto) {
        return new ResponseEntity<>(dentistService.save(dentistDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistDto> updateDentist(@RequestBody DentistDto dentistDto, @PathVariable Long id) {
        return ResponseEntity.ok(dentistService.update(dentistDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentist(@PathVariable Long id) {
        if (dentistService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        dentistService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
