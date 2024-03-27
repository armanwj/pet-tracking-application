package com.tractive.petapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DOG")
public class Dog extends Pet {
    public Dog() {
        this.petType = PetType.DOG;
    }
}
