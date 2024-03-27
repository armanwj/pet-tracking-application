package com.tractive.petapplication.repository;

import com.tractive.petapplication.dto.OutOfSavingZoneDto;
import com.tractive.petapplication.entity.Cat;
import com.tractive.petapplication.entity.Dog;
import com.tractive.petapplication.entity.Pet;
import com.tractive.petapplication.entity.PetType;
import com.tractive.petapplication.entity.TrackerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    private Pet pet;


    @BeforeEach
    public void setupTestData() {
        pet = new Cat();
    }

    @Test
    public void givenCat_whenSave_thenReturnCat() {
        Pet saved = (Pet) petRepository.save(pet);
        assert (saved != null);
        assert (saved.getPetType() == PetType.CAT);

    }

    @Test
    public void givenPetList_whenFindAll_thenPetList() {

        Pet cat1 = new Cat();
        Pet cat2 = new Cat();
        Pet dog = new Dog();

        petRepository.save(cat1);
        petRepository.save(cat2);
        petRepository.save(dog);

        List<Pet> pets = petRepository.findAll();

        assert (!pets.isEmpty());
        assert (pets.size() == 3);
    }

    @Test
    public void givenPetList_whenFindByPetType_thenCatList() {

        Pet cat1 = new Cat();
        Pet cat2 = new Cat();
        Pet dog = new Dog();

        petRepository.save(cat1);
        petRepository.save(cat2);
        petRepository.save(dog);

        List<Pet> pets = petRepository.findByPetType(PetType.CAT);

        assert (!pets.isEmpty());
        assert (pets.size() == 2);
        assert (pets.get(0).getPetType() == PetType.CAT);
        assert (pets.get(1).getPetType() == PetType.CAT);

    }

    @Test
    public void givenPetList_whenFindPetsOutsidePowerSavingZone_thenPetsOutsidePowerSavingZoneList() {

        Pet cat1 = new Cat();
        cat1.setInZone(false);
        cat1.setTrackerType(TrackerType.CAT_SMALL);
        Pet cat2 = new Cat();
        cat2.setInZone(false);
        cat2.setTrackerType(TrackerType.CAT_SMALL);
        Pet cat3 = new Cat();
        cat3.setInZone(false);
        cat3.setTrackerType(TrackerType.CAT_BIG);
        Pet dog = new Dog();
        dog.setInZone(false);
        dog.setTrackerType(TrackerType.DOG_BIG);

        petRepository.save(cat1);
        petRepository.save(cat2);
        petRepository.save(cat3);
        petRepository.save(dog);

        List<OutOfSavingZoneDto> pets = petRepository.findPetsOutsidePowerSavingZone();

        assert (!pets.isEmpty());
        assert (pets.size() == 3);
        TrackerType trackerType = pets.stream().filter(p -> p.getCount() == 2).map(m -> m.getTrackerType()).findFirst().orElse(null);
        assert (trackerType != null);
        assert (trackerType == TrackerType.CAT_SMALL);


    }


}