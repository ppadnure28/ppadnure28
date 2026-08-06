package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.MealIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealIngredientRepository extends JpaRepository<MealIngredient,Long>
{
    boolean existsByMeal_IdAndIngredient_Id(Long mealId, Long ingredientId);


}
