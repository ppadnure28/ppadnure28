package com.tiffino.review.dto;

import lombok.Data;

@Data
public class ReviewRequestDTO {  //while creating a review
    private Long userId;
    private Long mealId;
    private Long orderId;
    private Integer rating;
    private String Comment;
    private String status;

    public String getComment() {
        return Comment;
    }

    public Integer getRating() {
        return rating;
    }

    public String getStatus() {
        return status;
    }
}
