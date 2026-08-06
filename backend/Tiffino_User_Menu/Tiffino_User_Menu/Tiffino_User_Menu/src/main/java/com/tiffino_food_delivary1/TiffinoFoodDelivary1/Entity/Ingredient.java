package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
    public class Ingredient
    {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;
        private boolean isAllergen;

        @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
       // @JsonIgnoreProperties("ingredient")   //add
        private Set<MealIngredient> mealIngredients = new HashSet<>();

        


    }


