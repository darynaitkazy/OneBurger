package com.example.javaspringcourseproject.repos;

import com.example.javaspringcourseproject.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Integer> {
}
