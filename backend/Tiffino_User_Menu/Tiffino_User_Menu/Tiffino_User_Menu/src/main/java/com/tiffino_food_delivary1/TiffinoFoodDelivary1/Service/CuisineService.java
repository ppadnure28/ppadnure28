package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Cuisine;

import java.util.List;

public interface CuisineService
{
     Cuisine addCuisine(Cuisine cuisine);
    List<Cuisine>getAllCuisine();

    Cuisine getCuisineById(Long id);

    Cuisine updateCuisine(Long id,Cuisine cuisine);

    void deleteCuisine(Long id);

}
