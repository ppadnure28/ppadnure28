package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Ingredient;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository.IngredientRepository;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsServiceImpl implements IngredientsService
{
    private final MealRepository mealRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientsServiceImpl(MealRepository mealRepository, IngredientRepository ingredientRepository)
    {
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> saveAll(List<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }


    @Override
    public Ingredient create(String name, boolean isAllergen) {
        Ingredient ing = new Ingredient();
        ing.setName(name);
        ing.setAllergen(isAllergen);
        return ingredientRepository.save(ing);
    }

    @Override
    public List<Ingredient> list() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with ID: " + id));
    }

    @Override
    public Ingredient update(Long id, Ingredient ingredient) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with ID: " + id));

        existing.setName(ingredient.getName());
        existing.setAllergen(ingredient.isAllergen());

        return ingredientRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new RuntimeException("Ingredient not found with ID: " + id);
        }
        ingredientRepository.deleteById(id);
    }



}


