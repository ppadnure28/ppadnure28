package com.tiffino_food_delivary1.TiffinoFoodDelivary1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cuisine")
    public class Cuisine {

        @Id
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Long id;

        private String name;         //  Maharashtrian, South Indian
        private String description;  //  Spicy, coconut-based

        @OneToMany(mappedBy="cuisine",cascade=CascadeType.ALL,orphanRemoval=true)
        @Builder.Default
        private Set<Meal> meals=new HashSet<>();
        @Column(name = "origin_state")
        private String originState;  //  Maharashtra, Punjab
        //private String imageUrl;     // URL to cuisine image






}
