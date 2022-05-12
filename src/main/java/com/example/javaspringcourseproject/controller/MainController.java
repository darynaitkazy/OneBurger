package com.example.javaspringcourseproject.controller;

import com.example.javaspringcourseproject.model.Food;
import com.example.javaspringcourseproject.repos.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private FoodRepository foodRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);
        return "index";
    }

    @GetMapping("/food/add")
    public String foodAdd(Model model) {
        return "product-add";
    }

    @PostMapping("/food/add")
    public String foodPostAdd(@RequestParam int type, @RequestParam String name, @RequestParam String description,
                              @RequestParam double price, Model model, @RequestParam("file") MultipartFile file) throws IOException {
        Food food = new Food(type, name, description, price);

        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFilename));
            food.setFilename(resultFilename);
        }

        foodRepository.save(food);
        return "redirect:/";
    }

    @GetMapping("/food/{id}/edit")
    public String foodEdit(@PathVariable(value="id") Long id, Model model) {
        if (!foodRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Food> food = foodRepository.findById(id);
        ArrayList<Food> res = new ArrayList<>();
        food.ifPresent(res::add);
        model.addAttribute("foods", res);
        return "food-edit";
    }

    @PostMapping("/food/{id}/edit")
    public String foodPostUpdate(@PathVariable(value = "id") Long id, @RequestParam int type, @RequestParam String name, @RequestParam String description,
                              @RequestParam double price, Model model, @RequestParam("file") MultipartFile file) throws IOException {
        Food food = foodRepository.findById(id).orElseThrow();
        food.setType(type);
        food.setName(name);
        food.setDescription(description);
        food.setPrice(price);
        if (!file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFilename));
            food.setFilename(resultFilename);
        }
        foodRepository.save(food);

        return "redirect:/";
    }

    @PostMapping("/food/{id}/remove")
    public String foodPostDelete(@PathVariable(value = "id") Long id, Model model) throws IOException {
        Food food = foodRepository.findById(id).orElseThrow();

        foodRepository.delete(food);

        return "redirect:/";
    }

}
