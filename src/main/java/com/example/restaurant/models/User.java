package com.example.restaurant.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name="user_name")
    private String username;
    @Column(name="active")
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="image_id")
    private Image avatar;
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles=new HashSet<>();
    @Column(name = "password",length = 1000)
    private String password;
    @ManyToMany(mappedBy = "users")
    private Set<Dish> dishes=new HashSet<>();
    public void addDish(Dish dish){
        dishes.add(dish);
    }
    public void deleteDish(Dish dish){
        dishes.remove(dish);
    }
    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }
    public double getPriceCart(){
        int price=0;
        for(Dish i:dishes){
            price+=i.getPrice();
        }
        return price;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
