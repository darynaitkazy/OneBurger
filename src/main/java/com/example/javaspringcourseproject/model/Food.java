package com.example.javaspringcourseproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name, description;
    private float price;
    private String img;

    public Food() {

    }

    public Food(String name, String description, float price, String img) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }
}
