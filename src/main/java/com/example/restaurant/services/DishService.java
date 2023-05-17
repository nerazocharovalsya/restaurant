package com.example.restaurant.services;

import com.example.restaurant.models.Dish;
import com.example.restaurant.models.Image;
import com.example.restaurant.repository.DishRepository;
import com.example.restaurant.repository.ImageRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@Service
public class DishService {
    private final DishRepository dishRepository;
    private final ImageRepository imageRepository;

    public DishService(DishRepository dishRepository, ImageRepository imageRepository) {
        this.dishRepository = dishRepository;
        this.imageRepository = imageRepository;
    }

    public List<Dish> getDishesByNames(List<String> dishes){
        return dishRepository.getDishesByNames(dishes);
    }
    public void saveDish(Dish dish, MultipartFile file) throws IOException {
        if(file.getSize()!=0) {

            Image image = toImageEntity(file);
            imageRepository.save(image);
            dish.setImage(image);
            image.setDish(dish);

        }

        dishRepository.save(dish);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image=new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }
    public void deleteById(int id){
//        Dish footballer=dishRepository.getDishById(id);
//        for(FootballUniform uniform : footballer.getFootballUniforms()){
//            uniform.deleteFootballer(footballer);
//        }
//        footballerRepository.save(footballer);
        dishRepository.deleteById(id);
    }
    public Dish getById(int id){
        return dishRepository.getDishById(id);
    }

}
