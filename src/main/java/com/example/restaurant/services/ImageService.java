package com.example.restaurant.services;

import com.example.restaurant.models.Image;
import com.example.restaurant.repository.ImageRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component

public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository image) {
        this.imageRepository = image;
    }


    public Image getById(int id) {
        return imageRepository.getImageById(id);
    }
}
