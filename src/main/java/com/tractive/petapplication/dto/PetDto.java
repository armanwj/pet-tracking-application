package com.tractive.petapplication.dto;

import com.tractive.petapplication.entity.PetType;
import com.tractive.petapplication.entity.TrackerType;

public class PetDto {
    Long id;
    PetType petType;
    TrackerType trackerType;
    Integer ownerId;
    Boolean inZone;
    Boolean lostTracker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getInZone() {
        return inZone;
    }

    public void setInZone(Boolean inZone) {
        this.inZone = inZone;
    }

    public Boolean getLostTracker() {
        return lostTracker;
    }

    public void setLostTracker(Boolean lostTracker) {
        this.lostTracker = lostTracker;
    }
}
