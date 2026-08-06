package com.tiffino_food_delivary1.TiffinoFoodDelivary1.MealDTO;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Cuisine;
import com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity.Ingredient;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealRequest
{
    @NotBlank(message = "name is required")
    private String name;
    private String description;
    @NotNull(message = "cuisineId is required")
    private Long cuisine_id;
  
    private Boolean isVegetarian;
    private Boolean isVegan;
    private Boolean isNonVegetarian;
    private String spiceLevel;
    private Set<String> portionSizeOptions;
    private String imageUrl;
    // Accept JSON string or structured if you switch later to Map
    private String nutritionalInfo;
    @PositiveOrZero
    private Integer calories;
    @PositiveOrZero
    private Double proteins;
    @PositiveOrZero
    private Double fats;
    @PositiveOrZero
    private Double carbohydrates;
    private Set<String> allergens;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate availableDate;

    // ✅ Add this field
    private List<Ingredient> ingredients;

}
