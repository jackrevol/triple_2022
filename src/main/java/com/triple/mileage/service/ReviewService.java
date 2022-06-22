package com.triple.mileage.service;

import com.triple.mileage.dao.Review;
import com.triple.mileage.dto.EventDTO;
import com.triple.mileage.log.PointChangeLog;
import com.triple.mileage.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PhotoService photoService;

    public void addReview(EventDTO eventDTO) {
        int bounusPoint = 0;
        Boolean isFirstReview = !placeService.hasReview(eventDTO.getPlaceId());
        if (isFirstReview) {
            bounusPoint += 1;
        }
        if (eventDTO.getContent().length() > 0) {
            bounusPoint += 1;
        }
        if (eventDTO.getAttachedPhotoIds().size() > 0) {
            bounusPoint += 1;
        }

        Review review = new Review(eventDTO.getReviewId(),
                eventDTO.getUserId(),
                eventDTO.getPlaceId(),
                eventDTO.getContent(),
                isFirstReview,
                bounusPoint);

        userService.addUserPoint(eventDTO.getUserId(), bounusPoint);
        reviewRepository.save(review);
        photoService.createPhotos(eventDTO.getAttachedPhotoIds(), eventDTO.getReviewId());
        logger.info(new PointChangeLog(
                eventDTO.getUserId(),
                eventDTO.getReviewId(),
                eventDTO.getAction(),
                eventDTO.getType(),
                bounusPoint)
                .toString());
    }

    public void modReview(EventDTO eventDTO) {
        //갱신된 리뷰 점수 계산
        Review review = reviewRepository.findById(eventDTO.getReviewId())
                .orElseThrow(NullPointerException::new);
        int reviewNewPoint = (eventDTO.getContent().length() > 0 ? 1 : 0)
                + (eventDTO.getAttachedPhotoIds().size() > 0 ? 1 : 0)
                + (review.isFirstReview() ? 1 : 0);
        int reviewOrgPoint = review.getPoint();
        userService.modUserPoint(eventDTO.getUserId(), reviewOrgPoint, reviewNewPoint);

        //리뷰 갱신
        review.setContent(eventDTO.getContent());
        review.setPoint(reviewNewPoint);
        reviewRepository.save(review);

        photoService.updatePhotosByModReview(eventDTO.getAttachedPhotoIds(), eventDTO.getReviewId());
        logger.info(new PointChangeLog(
                eventDTO.getUserId(),
                eventDTO.getReviewId(),
                eventDTO.getAction(),
                eventDTO.getType(),
                reviewNewPoint - reviewOrgPoint)
                .toString());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteReview(EventDTO eventDTO) {
        String reviewId = eventDTO.getReviewId();
        Review review = reviewRepository.findById(eventDTO.getReviewId())
                .orElseThrow(NullPointerException::new);

        reviewRepository.deleteById(reviewId);
        photoService.deleteAllPhotoRelateReview(reviewId);
        userService.subtractUserPoint(eventDTO.getUserId(), review.getPoint());

        if (!reviewRepository.existsByPlaceId(eventDTO.getPlaceId())) {
            placeService.makeHasNoReview(eventDTO.getPlaceId());
        }
        logger.info(new PointChangeLog(
                eventDTO.getUserId(),
                eventDTO.getReviewId(),
                eventDTO.getAction(),
                eventDTO.getType(),
                -1 * review.getPoint())
                .toString());
    }

}
