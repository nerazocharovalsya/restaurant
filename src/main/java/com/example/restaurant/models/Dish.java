package com.example.restaurant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name= "name")
    private String name;
    @Column(name= "title")
    private String title;
    @Column(name= "composition")
    private String composition;
    @Column(name="price")
    private double price;
    @ManyToOne()
    @JoinColumn(name="category_id")

    private Category category;
    @OneToOne()
    @JoinColumn(name = "image_id")
    private Image image;
    @ManyToMany()
    private Set<User> users=new HashSet<>();
    public void addUser(User user){
        users.add(user);
    }
    public void deleteUser(User user){
        users.remove(user);
    }

}
