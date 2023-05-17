package com.example.restaurant;

import com.example.restaurant.models.Footballer;
import com.example.restaurant.services.FootballerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class KursovayaApplicationTests {
    final FootballerService footballerService;

    KursovayaApplicationTests( FootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @Test
    void contextLoads() {


    }

}
