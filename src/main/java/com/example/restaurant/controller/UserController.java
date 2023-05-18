package com.example.restaurant.controller;

import com.example.restaurant.models.User;
import com.example.restaurant.services.CategoryService;
import com.example.restaurant.services.DishService;
import com.example.restaurant.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final DishService dishService;
    private final CategoryService categoryService;


    public UserController(UserService userService, DishService dishService, CategoryService categoryService) {
        this.userService = userService;
        this.dishService = dishService;
        this.categoryService = categoryService;
    }
    @GetMapping("/registration")
    public String registration(){
//        System.out.println("allee");

        return "registration";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/registration")
    public String createUser(@RequestParam String firstname, @RequestParam String lastname,
                             @RequestParam String email, @RequestParam String password, Model model) {
       // System.out.println("reg");
        User user=new User();
        user.setPassword(password);
        user.setUsername(email);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/profil")
    public String getProfil(Principal principal, Model model){
        model.addAttribute("user",userService.getByUserName(principal.getName()));
        //System.out.println(principal.getName());
        return "profil";
    }
    @GetMapping("/")
    public String getHome(Principal principal, Model model){
        if(principal==null){
            model.addAttribute("Admin",false);
        }
        else {
            model.addAttribute("Admin", userService.getByUserName(principal.getName()).isAdmin());
        }
        model.addAttribute("categories",categoryService.getCategories());
        return "index";
    }
    @GetMapping("/cart")
    public String getCart(Principal principal,Model model){
        model.addAttribute("user",userService.getByUserName(principal.getName()));
        // System.out.println(userService.getByUserName(principal.getName()).getFootballUniforms().size());
        return "cart";
    }
//    @DeleteMapping("/cart/{id}")
//    public String getCart(@PathVariable Long id, Principal principal, Model model){
//        //System.out.println("Удаление");
//        dishService.deleteUser(userService.getByUserName(principal.getName()),
//                dishService.getFootballUniformById(id));
//        return "redirect:/cart";
//    }
}
