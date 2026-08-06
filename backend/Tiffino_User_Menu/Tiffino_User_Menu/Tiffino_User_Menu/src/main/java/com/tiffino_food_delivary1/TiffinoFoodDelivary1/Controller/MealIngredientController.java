/*package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Controller;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.MealIngredient;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service.MealIngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-ingredients")
public class MealIngredientController {

    private final MealIngredientService mealIngredientService;

    public MealIngredientController(MealIngredientService mealIngredientService) {
        this.mealIngredientService = mealIngredientService;
    }


    // Link Meal and Ingredient
    @PostMapping("/link/{mealId}/{ingredientId}")
    public MealIngredient linkMealIngredient(@PathVariable Long mealId, @PathVariable Long ingredientId) {
        return mealIngredientService.linkMealAndIngredient(mealId, ingredientId);
    }

    // Update a link
    @PutMapping("/{id}/{newMealId}/{newIngredientId}")
    public MealIngredient updateLink(@PathVariable Long id,
                                     @PathVariable Long newMealId,
                                     @PathVariable Long newIngredientId) {
        return mealIngredientService.updateMealIngredient(id, newMealId, newIngredientId);
    }

    // Delete a link
    @DeleteMapping("/{id}")
    public void deleteLink(@PathVariable Long id) {
        mealIngredientService.deleteMealIngredient(id);
    }

    // Get all links
    @GetMapping
    public List<MealIngredient> getAllLinks() {
        return mealIngredientService.getAllLinks();
    }
}*/

package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Controller;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.MealIngredient;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service.MealIngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/menu/meal-ingredients")
public class MealIngredientController {

    private final MealIngredientService mealIngredientService;

    public MealIngredientController(MealIngredientService mealIngredientService) {
        this.mealIngredientService = mealIngredientService;
    }

    // Create link
    @PostMapping("/link/{mealId}/{ingredientId}")
    public MealIngredient linkMealIngredient(@PathVariable Long mealId, @PathVariable Long ingredientId) {
        return mealIngredientService.linkMealAndIngredient(mealId, ingredientId);
    }

    // Update link
    @PutMapping("/{id}/{newMealId}/{newIngredientId}")
    public MealIngredient updateLink(@PathVariable Long id,
                                     @PathVariable Long newMealId,
                                     @PathVariable Long newIngredientId) {
        return mealIngredientService.updateMealIngredient(id, newMealId, newIngredientId);
    }

    // Delete link
    @DeleteMapping("/{id}")
    public void deleteLink(@PathVariable Long id) {
        mealIngredientService.deleteMealIngredient(id);
    }

    // Get all links
    @GetMapping
    public List<MealIngredient> getAllLinks() {
        return mealIngredientService.getAllLinks();
    }
}

