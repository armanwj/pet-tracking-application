package com.tractive.petapplication.dto;

import com.tractive.petapplication.entity.PetType;
import com.tractive.petapplication.entity.TrackerType;

public class OutOfSavingZoneDto {
    public OutOfSavingZoneDto(Long count, PetType petType, TrackerType trackerType) {
        this.count = count;
        this.petType = petType;
        this.trackerType = trackerType;
    }

    Long count;
    PetType petType;
    TrackerType trackerType;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public TrackerType getTrackerType() {
        return trackerType;
    }

    public void setTrackerType(TrackerType trackerType) {
        this.trackerType = trackerType;
    }
}
