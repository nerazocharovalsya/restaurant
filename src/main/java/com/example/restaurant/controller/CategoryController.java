package com.example.restaurant.controller;

import com.example.restaurant.models.Category;
import com.example.restaurant.services.CategoryService;
import com.example.restaurant.services.DishService;
import com.example.restaurant.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Component
@Controller
public class CategoryController {
    private final CategoryService categoryService;
    private final DishService dishService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, DishService dishService, UserService userService) {
        this.categoryService = categoryService;
        this.dishService = dishService;
        this.userService = userService;
    }

    @GetMapping("/categories")
    public String getCategories(Model model){
        model.addAttribute("categories",categoryService.getCategories());
        return "categories";
    }
    @PostMapping("/categories")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewCategory(@RequestParam(name = "dishes") List<String> dishes,
                                 @RequestParam String title,
                               @RequestParam MultipartFile file1) throws IOException {
        Category category=new Category();
        category.setDishes(dishService.getDishesByNames(dishes));
        category.setTitle(title);
        categoryService.saveCategory(category,file1);
        return "redirect:/categories";
    }


    @GetMapping("/categories/new")
    public String newCategory(Model model){
        model.addAttribute("dishes",dishService.getDishes());
        return "form_for_add_new_category";
    }
    @GetMapping("/categories/{nameCategory}")
    public String getCategory(@PathVariable String nameCategory, Model model){
        System.out.println("Получение категории");
        System.out.println(nameCategory);
        model.addAttribute("category",categoryService.getByEnName(nameCategory));
//        System.out.println(categoryService.getByEnName(nameCategory));
        return "category";
    }
//    @PostMapping("/footballUniforms/{id}")
////    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public String addFootballUniformInCart(@PathVariable Long id,Principal principal){
//
//        userService.addFootballUniform(userService.getByUserName(principal.getName()),
//                footballUniformService.getFootballUniformById(id));
//        return "redirect:/cart";
//    }
//    @DeleteMapping ("/footballUniforms/{id}")
////    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String deleteFootballUniform(@PathVariable Long id)
//    {
//        //System.out.println("Удаление формы");
//        footballUniformService.deleteFootballUniformByID(id);
//        return "redirect:/footballUniforms";
//    }

//    @GetMapping("/footballUniforms/{id}")
//    public String getFootballUniform(@PathVariable String id, Principal principal, Model model){
////        footballUniformService.getFootballUniformById(Integer.parseInt(id));
//        System.out.println(footballUniformService.getFootballUniformById(Long.parseLong(id)).getImages().size());
////
//        if(principal==null){
//            model.addAttribute("Admin",false);
//        }
//        else {
//            model.addAttribute("Admin", userService.getByUserName(principal.getName()).isAdmin());
//        }
//        model.addAttribute("footballForm",
//                footballUniformService.getFootballUniformById(Long.parseLong(id)));
//
//        return "footballUniform";
//    }
}
