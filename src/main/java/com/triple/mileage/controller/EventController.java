package com.triple.mileage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.mileage.dao.Photo;
import com.triple.mileage.dao.Place;
import com.triple.mileage.dao.Review;
import com.triple.mileage.dao.User;
import com.triple.mileage.dto.EventDTO;
import com.triple.mileage.repository.PhotoRepository;
import com.triple.mileage.repository.PlaceRepository;
import com.triple.mileage.repository.ReviewRepository;
import com.triple.mileage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class EventController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotoRepository photoRepository;


    @PostMapping("/events")
    public Long postReview(@RequestBody String requestBody) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        EventDTO eventDTO = mapper.readValue(requestBody, EventDTO.class);

        switch (eventDTO.getAction()) {
            case ADD: {
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

                User user = userRepository.findById(eventDTO.getUserId())
                        .orElse(new User(eventDTO.getUserId(), new Long(0)));
                user.setPoint(user.getPoint() + bounusPoint);
                userRepository.save(user);


                reviewRepository.save(review);

                for (String photoId : eventDTO.getAttachedPhotoIds()) {
                    Photo photo = new Photo(photoId, eventDTO.getReviewId());
                    photoRepository.save(photo);
                }

            }
            case MOD: {
                //갱신된 리뷰 점수 계산
                int modPoint = (eventDTO.getContent().length() > 0 ? 1 : 0)
                        + (eventDTO.getAttachedPhotoIds().size() > 0 ? 1 : 0);

                User user = userRepository.findById(eventDTO.getUserId())
                        .orElseThrow(NullPointerException::new);


                Review review = reviewRepository.findById(eventDTO.getReviewId())
                        .orElseThrow(NullPointerException::new);
                int orgPoint = review.getPoint();

                user.setPoint(user.getPoint() - orgPoint + modPoint);
                userRepository.save(user);

                review.setContent(eventDTO.getContent());
                reviewRepository.save(review);


                //TODO: 사진사라진거랑 새로생긴거 판별해서 지우고 업로드 하고 하기

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
            case DELETE: {
                String reviewId = eventDTO.getReviewId();
                Review review = reviewRepository.findById(eventDTO.getReviewId())
                        .orElseThrow(NullPointerException::new);
                reviewRepository.deleteById(reviewId);

                List<String> photoIds = eventDTO.getAttachedPhotoIds();
                photoIds.addAll(photoRepository.findAllByReviewId(review.getId())
                        .stream().map(photo -> photo.getId())
                        .collect(Collectors.toList()));
                photoRepository.deleteByIdIn(photoIds);

                User user = userRepository.findById(eventDTO.getUserId()).orElseThrow(NullPointerException::new);
                user.setPoint(user.getPoint() - review.getPoint());
                userRepository.save(user);

                if (!reviewRepository.existByPlaceId(eventDTO.getPlaceId())) {
                    Place place = placeRepository.findById(eventDTO.getPlaceId()).orElseThrow(NullPointerException::new);
                    place.setHasReview(false);
                    placeRepository.save(place);
                }
            }
        }
        System.out.println(eventDTO);
        return 0L;
    }
}
