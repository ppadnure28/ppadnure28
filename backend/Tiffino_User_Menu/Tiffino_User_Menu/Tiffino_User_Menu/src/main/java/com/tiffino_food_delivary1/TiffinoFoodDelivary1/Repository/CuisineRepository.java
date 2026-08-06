package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine,Long>
{

}
