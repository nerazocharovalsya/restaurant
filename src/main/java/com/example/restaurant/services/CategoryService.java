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

    public Category getByEnName(String name){
        return categoryRepository.getCategoryByEnName(name);
    }
    public void deleteCategoryByID(Long id){

        categoryRepository.deleteById(id);
    }

    public void saveCategory(Category category, MultipartFile file1) throws IOException {
        if(file1.getSize()!=0) {
            Image image = toImageEntity(file1);
            category.setImage(image);
        }
        char[] abcCyr =   {' ','а','б','в','г','д','е', 'ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у',
                'ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};
        String[] abcLat = {"-","a","b","v","g","d","e", "e","zh","z","i","j","k","l","m","n","o","p","r","s","t","u",
                "f","h", "c","ch","sh","shch","","","","e","yu","ya"};
        System.out.println(abcLat.length+" "+abcCyr.length);
        StringBuilder builder = new StringBuilder();
        String message=category.getTitle().toLowerCase();
        for (int i = 0; i < message.length(); i++) {
            boolean flag=false;
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                    flag=true;
                }
            }
            if (!flag){
                builder.append(message.charAt(i));
            }
        }
        category.setEnName(builder.toString());
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
