package com.example.javaspringcourseproject.repos;

import com.example.javaspringcourseproject.model.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {
}
