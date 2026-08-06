package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.MealDTO.MealRequest;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.MealDTO.MealResponse;

import java.time.LocalDate;
import java.util.List;

public interface MealService
{
    MealResponse createMeal(MealRequest request);
    MealResponse getMealById(Long id);
    List<MealResponse> getAllMeals();
    List<MealResponse> getMealsByCuisine(Long cuisineId);
    List<MealResponse> getMealsByDate(LocalDate date);
    MealResponse updateMeal(Long id, MealRequest request);
    void deleteMeal(Long id);

}
