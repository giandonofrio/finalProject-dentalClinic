package com.example.proyectofinal_clinica.service.impl;

import com.example.proyectofinal_clinica.model.AddressDto;
import com.example.proyectofinal_clinica.model.DentistDto;
import com.example.proyectofinal_clinica.persistence.entity.Address;
import com.example.proyectofinal_clinica.persistence.entity.Dentist;
import com.example.proyectofinal_clinica.persistence.repository.IAddressRepository;
import com.example.proyectofinal_clinica.service.IAddressService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressDto findById(@NotNull Long id) {
        Address address = addressRepository.getById(id);
        return mapToDto(address);
    }

    @Override
    public AddressDto save(@NotNull AddressDto addressDto) {
        Address address = mapToEntity(addressDto);
        Address addressSaved = addressRepository.save(address);
        return mapToDto(addressSaved);
    }

    @Override
    public void deleteById(Long id) {
        Address address = addressRepository.getById(id);
        addressRepository.delete(address);
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        Address address = mapToEntity(addressDto);
        Address addressSaved = addressRepository.save(address);
        return mapToDto(addressSaved);
    }

    @Override
    public List<AddressDto> findAll() {
        List<Address> addressesList = addressRepository.findAll();
        return addressesList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    //---- Mapper ----//
    private AddressDto mapToDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    private Address mapToEntity(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }


}
