package com.tiffino_food_delivary1.TiffinoFoodDelivary1.MealDTO;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class MealResponse {
        private Long id;
        private String name;
        private String description;
        private Long cuisine_id;
        private String cuisineName;
        private Boolean isVegetarian;
        private Boolean isVegan;
        private Boolean isNonVegetarian;
        private String spiceLevel;
        private Set<String> portionSizeOptions;
        private String imageUrl;
        private String nutritionalInfo;
        private Integer calories;
        private Double proteins;
        private Double fats;
        private Double carbohydrates;
        private Set<String> allergens;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate availableDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;


    }

