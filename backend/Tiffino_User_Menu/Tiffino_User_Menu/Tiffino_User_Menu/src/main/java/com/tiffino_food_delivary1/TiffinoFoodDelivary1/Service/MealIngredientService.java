package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.MealIngredient;

import java.util.List;


    public interface MealIngredientService
    {
        MealIngredient linkMealAndIngredient(Long mealId, Long ingredientId);
        MealIngredient updateMealIngredient(Long id, Long newMealId, Long newIngredientId);
        void deleteMealIngredient(Long id);
        List<MealIngredient> getAllLinks();
    }


