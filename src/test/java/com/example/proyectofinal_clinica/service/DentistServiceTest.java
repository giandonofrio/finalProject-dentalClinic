package com.example.proyectofinal_clinica.service;

import com.example.proyectofinal_clinica.exceptions.ResourceNotFoundException;
import com.example.proyectofinal_clinica.model.DentistDto;
import com.example.proyectofinal_clinica.persistence.entity.Dentist;
import com.example.proyectofinal_clinica.service.impl.DentistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistServiceTest {
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private DentistService dentistService;
    private DentistDto dentistDto;

    @BeforeEach
    public void setUp() {
        dentistDto = new DentistDto("Juan", "Perez", "AE060CA");

    }

    @Test
    public void SaveDentistTest() throws ResourceNotFoundException {
        DentistDto dentistDto = dentistService.save(this.dentistDto);
        assertNotNull(dentistDto);
    }

    @Test
    public void updateDentistTest() throws ResourceNotFoundException {
        DentistDto dentistDto = dentistService.save(this.dentistDto);
        dentistDto.setName("Juanito");
        DentistDto dentistDtoUpdated = dentistService.update(dentistDto);
        assertNotNull(dentistDtoUpdated);
        assertEquals("Juanito", dentistDtoUpdated.getName());

    }

    @Test
    public void deleteDentistTest() throws ResourceNotFoundException {
        DentistDto dentistDto = dentistService.save(this.dentistDto);
        dentistService.deleteById(dentistDto.getId());
        assertThrows(ResourceNotFoundException.class, () -> dentistService.findById(dentistDto.getId()));
    }

    @Test
    public void getAllDentistTest() throws ResourceNotFoundException {
        dentistService.save(this.dentistDto);

        assertNotNull(dentistService.findAll());
    }
}
