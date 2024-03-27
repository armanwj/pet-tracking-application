package com.tractive.petapplication.repository;

import com.tractive.petapplication.dto.OutOfSavingZoneDto;
import com.tractive.petapplication.entity.Pet;
import com.tractive.petapplication.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository<T extends Pet> extends JpaRepository<T, Long> {

    @Query("select e from #{#entityName} as e where e.petType = ?1 ")
    List<T> findByPetType(PetType petType);

    @Query("select new com.tractive.petapplication.dto.OutOfSavingZoneDto(count(e), e.petType, e.trackerType) " +
            "from  #{#entityName} as e where e.inZone = false group by e.petType, e.trackerType")
    List<OutOfSavingZoneDto> findPetsOutsidePowerSavingZone();
}

