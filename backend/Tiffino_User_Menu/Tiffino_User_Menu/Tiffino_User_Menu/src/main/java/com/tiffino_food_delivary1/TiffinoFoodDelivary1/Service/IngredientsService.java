package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Ingredient;

import java.util.List;

public interface IngredientsService
{
    // Create or Save
    Ingredient save(Ingredient ingredient);

    List<Ingredient> saveAll(List<Ingredient> ingredients);

    // Create (by params)
    Ingredient create(String name, boolean isAllergen);

    // Read all
    List<Ingredient> list();

    // Read by ID
    Ingredient getById(Long id);

    // Update
    Ingredient update(Long id, Ingredient ingredient);

    // Delete
    void delete(Long id);

}
