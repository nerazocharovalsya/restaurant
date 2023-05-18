package com.example.restaurant.controller;

import com.example.restaurant.models.Dish;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.services.DishService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Component
@Controller
public class DishController {
    private final DishService dishService;
    private final UserRepository userRepository;

    public DishController(DishService dishService, UserRepository userRepository) {
        this.dishService = dishService;
        this.userRepository = userRepository;
    }
//    @DeleteMapping("/category/{id}")
//    public String deleteFootballUniform(@PathVariable int id)
//    {
//        dishService.deleteById(id);
//        return "redirect:/footballers";
//    }
//    @GetMapping("/dishes")
//    public String getFootballer(Model model){
//       // System.out.println(footballerService.getFootballers().size());
//        model.addAttribute("footballers",dishService.getDishes());
//        return "footballers";
//    }
//    @GetMapping("/footballers/{id}")
//    public String getFootballer(@PathVariable int id, Model model, Principal principal){
//
//        model.addAttribute("footballer",footballerService.getById(id));
//        model.addAttribute("Admin",userRepository.findByEmail(principal.getName()).isAdmin());
//
//        return "footballer";
//
//    }
    @GetMapping("/dishes")
    public String getDishes(Model model){
       // System.out.println(footballerService.getFootballers().size());
        model.addAttribute("dishes",dishService.getDishes());
        return "dishes";
    }
    @GetMapping("/dishes/new")
    public String newDish(){
        return "form_for_add_new_dish";
    }
    @PostMapping("/dishes")

    public String addNewDish(@RequestParam String name,@RequestParam String title,
                                 @RequestParam int price,@RequestParam String composition,
                                 @RequestParam MultipartFile file1) throws IOException {
        System.out.println("Добавление");
        Dish dish=new Dish();
        dish.setName(name);
        dish.setPrice(price);
        dish.setTitle(title);
        dish.setComposition(composition);
        dishService.saveDish(dish,file1);
        return "redirect:/dishes";
    }

}
