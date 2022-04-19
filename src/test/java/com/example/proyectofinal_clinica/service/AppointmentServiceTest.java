package com.example.proyectofinal_clinica.service;

import com.example.proyectofinal_clinica.exceptions.ResourceNotFoundException;
import com.example.proyectofinal_clinica.model.AddressDto;
import com.example.proyectofinal_clinica.model.AppointmentDto;
import com.example.proyectofinal_clinica.model.DentistDto;
import com.example.proyectofinal_clinica.model.PatientDto;
import com.example.proyectofinal_clinica.persistence.entity.Appointment;
import com.example.proyectofinal_clinica.service.impl.AppointmentService;
import com.example.proyectofinal_clinica.service.impl.DentistService;
import com.example.proyectofinal_clinica.service.impl.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DentistService dentistService;

    private AppointmentDto appointmentDto;
    private PatientDto patientDto;
    private DentistDto dentistDto;

    @BeforeEach
    public void setUp() {
        AddressDto addressDto = new AddressDto( "Calle de la Calle", "1235", "12345", "123456789");
        dentistDto = new DentistDto("Juan", "Perez", "AE060CA");
        patientDto = new PatientDto("Juan", "Perez", "123456789", LocalDate.now(), addressDto);
    }

    @Test
    public void createAppointmentTest() throws ResourceNotFoundException {
        DentistDto dentistDto = dentistService.save(this.dentistDto);
        PatientDto patientDto = patientService.save(this.patientDto);
        appointmentDto = new AppointmentDto(LocalDate.now(), patientDto, dentistDto);
        AppointmentDto appointmentDto = appointmentService.save(this.appointmentDto);
        assertNotNull(appointmentDto);
    }

    @Test
    public void deleteAppointmentTest() throws ResourceNotFoundException {
        DentistDto dentistDto = dentistService.save(this.dentistDto);
        PatientDto patientDto = patientService.save(this.patientDto);
        appointmentDto = new AppointmentDto(LocalDate.now(), patientDto, dentistDto);
        AppointmentDto appointmentDto = appointmentService.save(this.appointmentDto);
        appointmentService.deleteById(appointmentDto.getId());
        assertThrows(ResourceNotFoundException.class, () -> appointmentService.findById(appointmentDto.getId()));
    }

    @Test
    public void updateAppointmentTest() throws ResourceNotFoundException {
        DentistDto dentistDto = dentistService.save(this.dentistDto);
        PatientDto patientDto = patientService.save(this.patientDto);
        AppointmentDto appointmentDto = new AppointmentDto(LocalDate.now(), patientDto, dentistDto);

        AppointmentDto updatedAppointmentDto = appointmentService.save(appointmentDto);
        updatedAppointmentDto.setDateAppointment(LocalDate.of(2020, 1, 1));

        assertNotEquals(appointmentDto.getDateAppointment(), updatedAppointmentDto.getDateAppointment());


    }
}
