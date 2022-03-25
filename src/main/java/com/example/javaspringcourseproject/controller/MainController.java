package com.example.javaspringcourseproject.controller;

import com.example.javaspringcourseproject.model.Food;
import com.example.javaspringcourseproject.repos.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);
        return "index";
    }
}
