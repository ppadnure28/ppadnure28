package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
    @Entity
    @Table(name = "meal_ingredient")
    public class MealIngredient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // surrogate PK

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;



    @ManyToOne
        @JoinColumn(name = "ingredient_id", nullable = false)
        private Ingredient ingredient;


    public MealIngredient(Meal meal, Ingredient ingredient)
    { this.meal=meal;
        this.ingredient=ingredient;
    }


}


