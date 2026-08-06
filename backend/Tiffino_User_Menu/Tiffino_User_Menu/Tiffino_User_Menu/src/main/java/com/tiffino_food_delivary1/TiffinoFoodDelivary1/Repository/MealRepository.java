package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Long>
{

}
