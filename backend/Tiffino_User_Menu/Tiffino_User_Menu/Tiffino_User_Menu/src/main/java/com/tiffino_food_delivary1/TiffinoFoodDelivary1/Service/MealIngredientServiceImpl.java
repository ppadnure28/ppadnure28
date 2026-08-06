package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service;


import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Ingredient;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Meal;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.MealIngredient;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository.IngredientRepository;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository.MealIngredientRepository;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealIngredientServiceImpl implements MealIngredientService {

    private final MealRepository mealRepo;
    private final IngredientRepository ingredientRepo;
    private final MealIngredientRepository mealIngredientRepo;

    public MealIngredientServiceImpl(MealRepository mealRepo,
                                     IngredientRepository ingredientRepo,
                                     MealIngredientRepository mealIngredientRepo) {
        this.mealRepo = mealRepo;
        this.ingredientRepo = ingredientRepo;
        this.mealIngredientRepo = mealIngredientRepo;
    }

    @Override
    public MealIngredient linkMealAndIngredient(Long mealId, Long ingredientId) {
        Meal meal = mealRepo.findById(mealId)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found: " + mealId));
        Ingredient ingredient = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + ingredientId));

        MealIngredient mealIngredient = new MealIngredient(meal, ingredient);
        return mealIngredientRepo.save(mealIngredient);
    }

    @Override
    public MealIngredient updateMealIngredient(Long id, Long newMealId, Long newIngredientId) {
        MealIngredient existing = mealIngredientRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Link not found: " + id));

        Meal newMeal = mealRepo.findById(newMealId)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found: " + newMealId));
        Ingredient newIngredient = ingredientRepo.findById(newIngredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + newIngredientId));

        existing.setMeal(newMeal);
        existing.setIngredient(newIngredient);

        return mealIngredientRepo.save(existing);
    }

    @Override
    public void deleteMealIngredient(Long id) {
        if (!mealIngredientRepo.existsById(id)) {
            throw new IllegalArgumentException("Link not found: " + id);
        }
        mealIngredientRepo.deleteById(id);
    }

    @Override
    public List<MealIngredient> getAllLinks() {
        return mealIngredientRepo.findAll();
    }
}
