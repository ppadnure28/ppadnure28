package com.example.tiffino_admin_review.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Review {
    private Long id;
    private String comment;
    private Integer rating;
    private LocalDateTime createdAt;
    private Long userId;
    private Long mealId;
}
