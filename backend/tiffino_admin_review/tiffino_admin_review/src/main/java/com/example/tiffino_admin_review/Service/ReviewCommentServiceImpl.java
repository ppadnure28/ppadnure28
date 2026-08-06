package com.example.tiffino_admin_review.Service;


import com.example.tiffino_admin_review.DTO.Review;
import com.example.tiffino_admin_review.Entity.ReviewComment;
import com.example.tiffino_admin_review.Repository.ReviewCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ReviewCommentServiceImpl implements ReviewCommentService{


    @Autowired
    private ReviewCommentRepository reviewCommentRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${review.service.url}")
    private String REVIEW_SERVICE_URL;

    @Override
    public ReviewComment addReply(Long reviewId, Long adminId, String reply) {
        // Fetch review from user service
        Review review = restTemplate.getForObject(
                REVIEW_SERVICE_URL + reviewId, Review.class  //convert json into review obj
        );

        if (review == null) {
            throw new RuntimeException("Review not found in user service");
        }

        // To save admin reply in review_Comment table
        ReviewComment comment = new ReviewComment();

        comment.setReviewId(reviewId); // referencing to review of user role
        comment.setAdminId(adminId);
        comment.setReply(reply);

        return reviewCommentRepo.save(comment);
    }

    @Override
    public List<ReviewComment> getRepliesByReviewId(Long reviewId) {
        return reviewCommentRepo.findByReviewIdIs(reviewId);
    }

    @Override
    public List<ReviewComment> getAllReviews() {
        return reviewCommentRepo.findAll();
    }

    @Override
    public ReviewComment updateReply(Long reviewId, String newReply) {
        ReviewComment comment = reviewCommentRepo.findByReviewId(reviewId)
                .orElseThrow(() -> new RuntimeException("Reply not found"));
        comment.setReply(newReply);
        return reviewCommentRepo.save(comment);
    }

    @Override
    public void deleteReply(Long reviewId) {
        ReviewComment exist= reviewCommentRepo.findByReviewId(reviewId)
                .orElseThrow(()->new RuntimeException("Can't delete. Review not found with ID: "+reviewId));
        reviewCommentRepo.delete(exist);
    }

}
