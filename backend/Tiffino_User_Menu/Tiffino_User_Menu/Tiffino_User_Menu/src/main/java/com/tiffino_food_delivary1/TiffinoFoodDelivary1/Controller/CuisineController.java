package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Controller;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Cuisine;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/menu/cuisines")
public class CuisineController
{
    @Autowired
    private CuisineService cuisineService;

    @PostMapping
    public Cuisine addCuisine(@RequestBody Cuisine cuisine)
    {
        return cuisineService.addCuisine(cuisine);
    }


    @GetMapping
    public List<Cuisine> getAllCuisines()
    {
        return cuisineService.getAllCuisine();
    }

    @GetMapping("/{id}")
    public Cuisine getCuisineById(@PathVariable Long id)
    {
        return cuisineService.getCuisineById(id);
    }

    //Update
    @PutMapping("/{id}")
    public Cuisine updateCuisine(@PathVariable Long id, @RequestBody Cuisine cuisine) {
      return cuisineService.updateCuisine(id, cuisine);
    }

    @DeleteMapping("/{id}")
    public String deleteCuisine(@PathVariable Long id) {
        cuisineService.deleteCuisine(id);
        return "Cuisine with ID " + id + " deleted successfully.";
    }



}
