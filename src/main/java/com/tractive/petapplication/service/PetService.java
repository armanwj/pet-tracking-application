package com.tractive.petapplication.service;

import com.tractive.petapplication.dto.OutOfSavingZoneDto;
import com.tractive.petapplication.entity.Pet;
import com.tractive.petapplication.entity.PetType;
import com.tractive.petapplication.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public <T extends Pet> T save(T pet) {
        return (T) petRepository.save(pet);
    }

    public <T extends Pet> List<T> getAllPets() {
        return petRepository.findAll();
    }

    public <T extends Pet> List<T> getPetsByPetType(PetType petType) {
        return petRepository.findByPetType(petType);
    }

    public List<OutOfSavingZoneDto> findPetsOutsidePowerSavingZone(){
        return petRepository.findPetsOutsidePowerSavingZone();
    }
}


