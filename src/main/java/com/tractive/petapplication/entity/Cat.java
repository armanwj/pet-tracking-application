package com.tractive.petapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "CAT")
public class Cat extends Pet {
    public Cat() {
        this.petType = PetType.CAT;
    }

    Boolean lostTracker;


    public Boolean getLostTracker() {
        return lostTracker;
    }

    public void setLostTracker(Boolean lostTracker) {
        this.lostTracker = lostTracker;
    }
}
