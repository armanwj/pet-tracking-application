package com.tractive.petapplication.controller;

import com.tractive.petapplication.dto.OutOfSavingZoneDto;
import com.tractive.petapplication.dto.PetDto;
import com.tractive.petapplication.entity.Cat;
import com.tractive.petapplication.entity.Dog;
import com.tractive.petapplication.entity.Pet;
import com.tractive.petapplication.entity.PetType;
import com.tractive.petapplication.exception.TypeNotExistsException;
import com.tractive.petapplication.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PetController {
    private final PetService service;
    private final ModelMapper modelMapper;

    public PetController(PetService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/pets")
    public ResponseEntity<List<PetDto>> getPets() {
        List<PetDto> results = service.getAllPets().stream()
                .map(this::convertToDto)
                .toList();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(value = "/pets/outside-saving-zone")
    public ResponseEntity<List<OutOfSavingZoneDto>> getPetsOutsidePowerSavingZone() {
        List<OutOfSavingZoneDto> results = service.findPetsOutsidePowerSavingZone();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(value = "/pets/pet-type/{petType}")
    public ResponseEntity<List<PetDto>> getPetsByPetType(@PathVariable("petType") String petType) throws TypeNotExistsException {
        PetType type;
        try {
            type = PetType.valueOf(petType);
        } catch (Exception e) {
            throw new TypeNotExistsException("pet type:" + petType + " does not exists!", e);
        }
        List<PetDto> results = service.getPetsByPetType(type).stream()
                .map(this::convertToDto)
                .toList();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/pets")
    public ResponseEntity<PetDto> insertPet(@RequestBody PetDto dto) {
        return new ResponseEntity<>(convertToDto(service.save(convertToEntity(dto))), HttpStatus.OK);
    }

    private <T extends Pet> PetDto convertToDto(T pet) {
        return modelMapper.map(pet, PetDto.class);
    }

    private <T extends Pet> T convertToEntity(PetDto petDto) {
        if (petDto.getPetType() == PetType.CAT) {
            return (T) modelMapper.map(petDto, Cat.class);
        } else if (petDto.getPetType() == PetType.DOG) {
            return (T) modelMapper.map(petDto, Dog.class);
        } else {
            return null;
        }
    }
}
