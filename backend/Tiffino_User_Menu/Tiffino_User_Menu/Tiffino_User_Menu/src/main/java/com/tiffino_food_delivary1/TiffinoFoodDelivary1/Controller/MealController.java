package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Controller;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.MealDTO.MealRequest;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.MealDTO.MealResponse;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/menu/meals")
public class MealController
{
    private final MealService mealService;

    public MealController(MealService mealService)
    {
        this.mealService = mealService;
    }

    @PostMapping
    public ResponseEntity<MealResponse> createMeal(@RequestBody MealRequest mealRequest) {
        MealResponse createdMeal = mealService.createMeal(mealRequest);
        return new ResponseEntity<>(createdMeal, HttpStatus.CREATED);
    }

    // Get Meal by ID
    @GetMapping("/{id}")
    public ResponseEntity<MealResponse> getMealById(@PathVariable Long id) {
        MealResponse response = mealService.getMealById(id);
        return ResponseEntity.ok(response);
    }

    // Get All Meals
    @GetMapping
    public ResponseEntity<List<MealResponse>> getAllMeals()
    {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    // Get Meals by Cuisine
    @GetMapping("/cuisine/{cuisineId}")
    public ResponseEntity<List<MealResponse>> getMealsByCuisine(@PathVariable Long cuisineId) {
        return ResponseEntity.ok(mealService.getMealsByCuisine(cuisineId));
    }

    // Get Meals by Date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<MealResponse>> getMealsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(mealService.getMealsByDate(localDate));
    }

    // Update Meal
    @PutMapping("/{id}")
    public ResponseEntity<MealResponse> updateMeal(@PathVariable Long id, @RequestBody MealRequest mealRequest) {
        MealResponse updatedMeal = mealService.updateMeal(id, mealRequest);
        return ResponseEntity.ok(updatedMeal);
    }

    // Delete Meal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
}
