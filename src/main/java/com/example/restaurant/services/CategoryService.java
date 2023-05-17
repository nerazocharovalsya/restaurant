package com.example.restaurant.services;

import com.example.restaurant.models.Category;

import com.example.restaurant.models.Dish;
import com.example.restaurant.models.Image;
import com.example.restaurant.repository.CategoryRepository;

import com.example.restaurant.repository.DishRepository;
import com.example.restaurant.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Component
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository, DishRepository dishRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;

        this.dishRepository = dishRepository;
    }


    public void deleteCategoryByID(Long id){
//        C footballUniform=footballUniformRepository.getFootballUniformById(id);
//        footballUniform.setFootballers(null);
//        System.out.println(footballUniform.getUsers().size());
//        for(User user : footballUniform.getUsers()){
//            user.deleteFootballUniform(footballUniform);
//            userRepository.save(user);
//        }
//        footballUniformRepository.save(footballUniform);
        categoryRepository.deleteById(id);
    }

    public void saveCategory(Category category, MultipartFile file1) throws IOException {
        if(file1.getSize()!=0) {
            Image image = toImageEntity(file1);
            category.setImage(image);
        }

        categoryRepository.save(category);
        for (Dish dish:category.getDishes()){
            dish.setCategory(category);
            dishRepository.save(dish);

        }
    }
    public Category getCategoryById(Long id){
        return categoryRepository.getCategoryById(id);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
