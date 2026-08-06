package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Controller;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Ingredient;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service.IngredientsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/menu/ingredients")
public class IngredientController {

    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    public Ingredient save(@RequestBody Ingredient ingredient) {
        return ingredientsService.save(ingredient);
    }

    @PostMapping("/bulk")
    public List<Ingredient> saveAll(@RequestBody List<Ingredient> ingredients) {
        return ingredientsService.saveAll(ingredients);
    }


    @GetMapping
    public List<Ingredient> list() {
        return ingredientsService.list();
    }

    @GetMapping("/{id}")
    public Ingredient getById(@PathVariable Long id) {
        return ingredientsService.getById(id);
    }

    @PutMapping("/{id}")
    public Ingredient update(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ingredientsService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ingredientsService.delete(id);
    }
}
