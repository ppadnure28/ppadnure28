package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,Long>
{
    boolean existsByNameIgnoreCase(String name);

}
