package com.tractive.petapplication.service;

import com.tractive.petapplication.entity.Cat;
import com.tractive.petapplication.entity.PetType;
import com.tractive.petapplication.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    private PetService petService;

    @BeforeEach
    public void setUp() {
        this.petService = new PetService(petRepository);
    }

    @Test
    public void when_one_pet_present_return_list_with_one_pet() {
        Mockito.when(petRepository.findAll()).thenReturn(singletonList(new Cat()));
        var pets = petService.getAllPets();
        assertTrue(pets.size() == 1);
        assertTrue(pets.get(0).getPetType() == PetType.CAT);
    }
}