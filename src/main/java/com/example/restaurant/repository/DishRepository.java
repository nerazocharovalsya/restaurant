package com.example.restaurant.repository;

import com.example.restaurant.models.Dish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface DishRepository extends JpaRepository<Dish,Long> {
    List<Dish> findAll();
    Dish save(Dish dish);
    @Query(value = "select dish.* from dish where dish.name in (:dishes)",nativeQuery = true)
    List<Dish> getDishesByNames(List<String> dishes);
    Dish getDishById(int id);
    void deleteById(int id);
}
