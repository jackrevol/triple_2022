package com.triple.mileage.service;

import com.triple.mileage.dao.Photo;
import com.triple.mileage.dao.Place;
import com.triple.mileage.dao.Review;
import com.triple.mileage.dto.EventDTO;
import com.triple.mileage.repository.PhotoRepository;
import com.triple.mileage.repository.PlaceRepository;
import com.triple.mileage.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    UserService userService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PhotoRepository photoRepository;

    public void addEvent(EventDTO eventDTO) {
        int bounusPoint = 0;
        Boolean isFirstReview = !placeRepository.findById(eventDTO.getPlaceId())
                .orElseThrow(NullPointerException::new)
                .isHasReview();

        Review review = new Review(eventDTO.getReviewId(),
                eventDTO.getUserId(),
                eventDTO.getPlaceId(),
                eventDTO.getContent(),
                isFirstReview,
                bounusPoint);

        if (isFirstReview) {
            bounusPoint += 1;
        }
        if (eventDTO.getContent().length() > 0) {
            bounusPoint += 1;
        }
        if (eventDTO.getAttachedPhotoIds().size() > 0) {
            bounusPoint += 1;
        }

        userService.addUserPoint(eventDTO.getUserId(),bounusPoint);


        reviewRepository.save(review);

        for (String photoId : eventDTO.getAttachedPhotoIds()) {
            Photo photo = new Photo(photoId, eventDTO.getReviewId());
            photoRepository.save(photo);
        }

    }

    public void modEvent(EventDTO eventDTO) {
        //갱신된 리뷰 점수 계산
        Review review = reviewRepository.findById(eventDTO.getReviewId())
                .orElseThrow(NullPointerException::new);
        int reviewNewPoint = (eventDTO.getContent().length() > 0 ? 1 : 0)
                + (eventDTO.getAttachedPhotoIds().size() > 0 ? 1 : 0)
                + (review.isFirstReview()?1:0);
        int reviewOrgPoint = review.getPoint();
        userService.modUserPoint(eventDTO.getUserId(),reviewOrgPoint,reviewNewPoint);

        //리뷰 갱신
        review.setContent(eventDTO.getContent());
        review.setPoint(reviewNewPoint);
        reviewRepository.save(review);


        //사진사라진거랑 새로생긴거 판별해서 지우고 업로드 하고 하기
        List<String> orgPhotoIdList = photoRepository.findAllByReviewId(review.getId())
                .stream().map(photo -> photo.getId())
                .collect(Collectors.toList());
        List<String> modPhotoIdList = eventDTO.getAttachedPhotoIds();

        Set<String> distinctPhotoList = orgPhotoIdList.stream()
                .distinct()
                .filter(modPhotoIdList::contains)
                .collect(Collectors.toSet());

        orgPhotoIdList.removeAll(distinctPhotoList);
        modPhotoIdList.removeAll(modPhotoIdList);

        photoRepository.deleteByIdIn(orgPhotoIdList);
        for (String photoId : modPhotoIdList) {
            Photo photo = new Photo(photoId, eventDTO.getReviewId());
            photoRepository.save(photo);
        }

    }

    public void deleteEvent(EventDTO eventDTO) {
        String reviewId = eventDTO.getReviewId();
        Review review = reviewRepository.findById(eventDTO.getReviewId())
                .orElseThrow(NullPointerException::new);
        reviewRepository.deleteById(reviewId);

        List<String> photoIds = eventDTO.getAttachedPhotoIds();
        photoIds.addAll(photoRepository.findAllByReviewId(review.getId())
                .stream().map(photo -> photo.getId())
                .collect(Collectors.toList()));
        photoRepository.deleteByIdIn(photoIds);

        userService.subtractUserPoint(eventDTO.getUserId(),review.getPoint());

        if (!reviewRepository.existByPlaceId(eventDTO.getPlaceId())) {
            Place place = placeRepository.findById(eventDTO.getPlaceId()).orElseThrow(NullPointerException::new);
            place.setHasReview(false);
            placeRepository.save(place);
        }
    }


}
