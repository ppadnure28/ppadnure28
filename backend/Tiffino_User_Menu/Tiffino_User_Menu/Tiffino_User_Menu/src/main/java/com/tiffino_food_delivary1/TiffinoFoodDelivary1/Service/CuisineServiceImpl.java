package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Service;

import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Cuisine;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CuisineServiceImpl implements CuisineService
{
        @Autowired
        private CuisineRepository cuisineRepository;

        @Override
    public Cuisine addCuisine(Cuisine cuisine)
        {
           return cuisineRepository.save(cuisine);
        }

    @Override
    public List<Cuisine> getAllCuisine()
    {
        return cuisineRepository.findAll();
    }

    @Override
    public Cuisine getCuisineById(Long id) {
        return cuisineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuisine not found with id: " + id));
    }



    @Override
    public Cuisine updateCuisine(Long id, Cuisine updatedCuisine) {
        return cuisineRepository.findById(id)
                .map(existingCuisine -> {
                    existingCuisine.setName(updatedCuisine.getName());
                    existingCuisine.setDescription(updatedCuisine.getDescription());
                    existingCuisine.setOriginState(updatedCuisine.getOriginState());
                    //existingCuisine.setImageUrl(updatedCuisine.getImageUrl());
                    return cuisineRepository.save(existingCuisine);
                })
                .orElseThrow(() -> new RuntimeException("Cuisine not found with id: " + id));
    }

    @Override
    public void deleteCuisine(Long id) {
        if (!cuisineRepository.existsById(id)) {
            throw new RuntimeException("Cuisine not found with id: " + id);
        }
        cuisineRepository.deleteById(id);
    }





}
