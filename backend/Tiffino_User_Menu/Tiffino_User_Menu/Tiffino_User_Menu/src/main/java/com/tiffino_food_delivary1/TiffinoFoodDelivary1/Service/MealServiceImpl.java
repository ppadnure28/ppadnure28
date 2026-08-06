package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Cuisine;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Meal;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.MealDTO.MealRequest;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.MealDTO.MealResponse;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository.CuisineRepository;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository.MealRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MealServiceImpl implements MealService
{

    private final MealRepository mealRepository;
    private final CuisineRepository cuisineRepository;



    @Override
    public MealResponse createMeal(MealRequest req) {
        Cuisine cuisine = cuisineRepository.findById(req.getCuisine_id())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cuisine not found with ID: " + req.getCuisine_id()));

        Meal meal = mapToEntity(req);
        meal.setCuisine(cuisine);

        Meal saved = mealRepository.save(meal);
        return mapToResponse(saved);
    }

    @Override
    public MealResponse getMealById(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with ID: " + id));
        return mapToResponse(meal);
    }

    @Override
    public List<MealResponse> getAllMeals() {
        return mealRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MealResponse> getMealsByCuisine(Long cuisineId) {
        Cuisine cuisine = cuisineRepository.findById(cuisineId)
                .orElseThrow(() -> new IllegalArgumentException("Cuisine not found with ID: " + cuisineId));

        return mealRepository.findAll().stream()
                .filter(meal -> meal.getCuisine() != null && meal.getCuisine().getId().equals(cuisine.getId()))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MealResponse> getMealsByDate(LocalDate date) {
        return mealRepository.findAll().stream()
                .filter(meal -> date.equals(meal.getAvailableDate()))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MealResponse updateMeal(Long id, MealRequest request) {
        Meal existingMeal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with ID: " + id));

        Cuisine cuisine = cuisineRepository.findById(request.getCuisine_id())
                .orElseThrow(() -> new IllegalArgumentException("Cuisine not found with ID: " + request.getCuisine_id()));

        existingMeal.setName(request.getName());
        existingMeal.setDescription(request.getDescription());

        existingMeal.setIsVegetarian(request.getIsVegetarian());
        existingMeal.setIsVegan(request.getIsVegan());
        existingMeal.setIsNonVegetarian(request.getIsNonVegetarian());
        existingMeal.setSpiceLevel(request.getSpiceLevel());
        existingMeal.setPortionSizeOptions(request.getPortionSizeOptions());
        existingMeal.setImageUrl(request.getImageUrl());
        existingMeal.setNutritionalInfo(request.getNutritionalInfo());
        existingMeal.setCalories(request.getCalories());
        existingMeal.setProteins(request.getProteins());
        existingMeal.setFats(request.getFats());
        existingMeal.setCarbohydrates(request.getCarbohydrates());
        existingMeal.setAllergens(request.getAllergens());
        existingMeal.setAvailableDate(request.getAvailableDate());
        existingMeal.setCuisine(cuisine);

        Meal updatedMeal = mealRepository.save(existingMeal);
        return mapToResponse(updatedMeal);
    }


    @Override
    public void deleteMeal(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with ID: " + id));
        mealRepository.delete(meal);
    }

    // Mapping DTO to Entity
    private Meal mapToEntity(MealRequest req) {
        Meal meal = new Meal();
        meal.setName(req.getName());
        meal.setDescription(req.getDescription());
        meal.setIsVegetarian(req.getIsVegetarian());
        meal.setIsVegan(req.getIsVegan());
        meal.setIsNonVegetarian(req.getIsNonVegetarian());
        meal.setSpiceLevel(req.getSpiceLevel());
        meal.setPortionSizeOptions(req.getPortionSizeOptions());
        meal.setImageUrl(req.getImageUrl());
        meal.setNutritionalInfo(req.getNutritionalInfo());
        meal.setCalories(req.getCalories());
        meal.setProteins(req.getProteins());
        meal.setFats(req.getFats());
        meal.setCarbohydrates(req.getCarbohydrates());
        meal.setAllergens(req.getAllergens());
        meal.setAvailableDate(req.getAvailableDate());
        return meal;
    }

    // Mapping Entity to DTO
    private MealResponse mapToResponse(Meal meal) {
        MealResponse response = new MealResponse();

        response.setId(meal.getId());
        response.setName(meal.getName());
        response.setDescription(meal.getDescription());

        if (meal.getCuisine() != null) {
            response.setCuisine_id(meal.getCuisine().getId());
            response.setCuisineName(meal.getCuisine().getName());  // This is key
        }

       // response.setCuisineId(meal.getCuisine().getId());
       // response.setCuisineName(meal.getCuisine().getName());
        response.setIsVegetarian(meal.getIsVegetarian());
        response.setIsVegan(meal.getIsVegan());
        response.setIsNonVegetarian(meal.getIsNonVegetarian());
        response.setSpiceLevel(meal.getSpiceLevel());
        response.setPortionSizeOptions(meal.getPortionSizeOptions());
        response.setImageUrl(meal.getImageUrl());
        response.setNutritionalInfo(meal.getNutritionalInfo());
        response.setCalories(meal.getCalories());
        response.setProteins(meal.getProteins());
        response.setFats(meal.getFats());
        response.setCarbohydrates(meal.getCarbohydrates());
        response.setAllergens(meal.getAllergens());
        response.setAvailableDate(meal.getAvailableDate());
        response.setCreatedAt(meal.getCreatedAt());
        response.setUpdatedAt(meal.getUpdatedAt());
        return response;
    }


}

