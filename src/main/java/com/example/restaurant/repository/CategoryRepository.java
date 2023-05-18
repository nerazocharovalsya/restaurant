package com.example.restaurant.repository;

import com.example.restaurant.models.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category save(Category footballUniform);
    List<Category> findAll();
    @Transactional
    Category getCategoryById(Long id);
    Category getCategoryByEnName(String name);
}
