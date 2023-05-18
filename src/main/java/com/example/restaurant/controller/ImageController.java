package com.example.restaurant.controller;

import com.example.restaurant.models.Image;
import com.example.restaurant.services.ImageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@Component

public class ImageController {

    private final ImageService imageFootballUniformService;

    public ImageController(ImageService imageService) {
        this.imageFootballUniformService = imageService;
    }

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable("id") int id) {
        //System.out.println(this.imageFootballUniformService);
        Image image = imageFootballUniformService.getById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}
