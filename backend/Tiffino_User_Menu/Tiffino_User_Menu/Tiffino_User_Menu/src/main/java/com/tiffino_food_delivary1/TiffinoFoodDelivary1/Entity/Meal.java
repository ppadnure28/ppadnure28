package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Meal JPA entity linked to Cuisine via @ManyToOne.
 * - portionSizeOptions and allergens are stored in element collection tables.
 * - nutritionalInfo stored as JSON string in a TEXT column.
 */

@Entity
@Table(name = "meals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Link to Cuisine entity (foreign key cuisine_id).
     * JsonIgnoreProperties prevents infinite loop when serializing.
     */
    @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "cuisine_id", nullable = false)
    @JsonIgnoreProperties({"meals"})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude


    @JoinColumn(name = "cuisine_id", nullable = false)
    private Cuisine cuisine;

    @Column(name = "is_vegetarian")
    private Boolean isVegetarian = false;

    @Column(name = "is_vegan")
    private Boolean isVegan = false;

    @Column(name = "is_non_vegetarian")
    private Boolean isNonVegetarian = false;



    @Column(name = "spice_level")
    private String spiceLevel; // "Mild", "Medium", "Spicy"

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "meal_portion_sizes", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "portion_size")
    @Builder.Default

    private Set<String> portionSizeOptions = new HashSet<>();

    @Column(name = "image_url")
    private String imageUrl;

    @Lob
    @Column(name = "nutritional_info", columnDefinition = "TEXT")
    private String nutritionalInfo; // JSON string or plain text

    private Integer calories;

    private Double proteins;

    private Double fats;

    private Double carbohydrates;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "meal_allergens", joinColumns = @JoinColumn(name = "meal_id"))

    @Builder.Default
    @Column(name = "allergen")
    private Set<String> allergens = new HashSet<>();

    @Column(name = "available_date")
    private LocalDate availableDate; // yyyy-MM-dd in JSON

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // MealIngredient join
    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MealIngredient> mealIngredients = new HashSet<>();


}
