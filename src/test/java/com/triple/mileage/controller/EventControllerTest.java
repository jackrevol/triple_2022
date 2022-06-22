package com.triple.mileage.controller;

import com.triple.mileage.dao.Photo;
import com.triple.mileage.dao.Place;
import com.triple.mileage.dao.Review;
import com.triple.mileage.dao.User;
import com.triple.mileage.dto.EventDTO;
import com.triple.mileage.enums.EventAction;
import com.triple.mileage.enums.EventType;
import com.triple.mileage.repository.PhotoRepository;
import com.triple.mileage.repository.PlaceRepository;
import com.triple.mileage.repository.ReviewRepository;
import com.triple.mileage.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventControllerTest {

    @Autowired
    EventController eventController;

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @Order(001)
    public void addTest() throws Exception {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setType(EventType.REVIEW);
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setReviewId("240a0658-dc5f-4878-9381-ebb7b2667772");
        eventDTO.setContent("\"좋아요!\"");
        List<String> attachedPhotoIds = new ArrayList<>();
        attachedPhotoIds.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
        attachedPhotoIds.add("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
        eventDTO.setAttachedPhotoIds(attachedPhotoIds);
        eventDTO.setUserId("3ede0ef2-92b7-4817-a5f3-0c575361f745");
        eventDTO.setPlaceId("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
        eventController.postReview(eventDTO);
        User user = userRepository.findById("3ede0ef2-92b7-4817-a5f3-0c575361f745").orElseThrow(Exception::new);
        Assertions.assertEquals(3,user.getPoint());
        List<Photo> photoList = photoRepository.findAll();
        Assertions.assertEquals(2,photoList.size());
        List<Place> placeList = placeRepository.findAll();
        Assertions.assertEquals(1,placeList.size());
        List<Review> reviewList = reviewRepository.findAll();
        Assertions.assertEquals(1,reviewList.size());
    }


    @Test
    @Order(002)
    public void modTest() throws Exception {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setType(EventType.REVIEW);
        eventDTO.setAction(EventAction.MOD);
        eventDTO.setReviewId("240a0658-dc5f-4878-9381-ebb7b2667772");
        eventDTO.setContent("\"좋아요!\"");
        List<String> attachedPhotoIds = new ArrayList<>();
        eventDTO.setAttachedPhotoIds(attachedPhotoIds);
        eventDTO.setUserId("3ede0ef2-92b7-4817-a5f3-0c575361f745");
        eventDTO.setPlaceId("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
        eventController.postReview(eventDTO);
        User user = userRepository.findById("3ede0ef2-92b7-4817-a5f3-0c575361f745").orElseThrow(Exception::new);
        Assertions.assertEquals(2,user.getPoint());
        List<Photo> photoList = photoRepository.findAll();
        Assertions.assertEquals(0,photoList.size());
        List<Place> placeList = placeRepository.findAll();
        Assertions.assertEquals(1,placeList.size());
        List<Review> reviewList = reviewRepository.findAll();
        Assertions.assertEquals(1,reviewList.size());
    }


    @Test
    @Order(003)
    public void deleteTest() throws Exception {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setType(EventType.REVIEW);
        eventDTO.setAction(EventAction.DELETE);
        eventDTO.setReviewId("240a0658-dc5f-4878-9381-ebb7b2667772");
        eventDTO.setContent("\"좋아요!\"");
        List<String> attachedPhotoIds = new ArrayList<>();
        eventDTO.setAttachedPhotoIds(attachedPhotoIds);
        eventDTO.setUserId("3ede0ef2-92b7-4817-a5f3-0c575361f745");
        eventDTO.setPlaceId("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
        eventController.postReview(eventDTO);
        User user = userRepository.findById("3ede0ef2-92b7-4817-a5f3-0c575361f745").orElseThrow(Exception::new);
        Assertions.assertEquals(0,user.getPoint());
        List<Photo> photoList = photoRepository.findAll();
        Assertions.assertEquals(0,photoList.size());
        List<Place> placeList = placeRepository.findAll();
        Assertions.assertEquals(1,placeList.size());
        List<Review> reviewList = reviewRepository.findAll();
        Assertions.assertEquals(0,reviewList.size());
    }

    @Test
    @Order(101)
    @Transactional
    public void addAgainTest() throws Exception {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setType(EventType.REVIEW);
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setReviewId("240a0658-dc5f-4878-9381-ebb7b2667772");
        eventDTO.setContent("\"좋아요!\"");
        List<String> attachedPhotoIds = new ArrayList<>();
        attachedPhotoIds.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8");
        attachedPhotoIds.add("afb0cef2-851d-4a50-bb07-9cc15cbdc332");
        eventDTO.setAttachedPhotoIds(attachedPhotoIds);
        eventDTO.setUserId("3ede0ef2-92b7-4817-a5f3-0c575361f745");
        eventDTO.setPlaceId("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
        eventController.postReview(eventDTO);
        try{
            eventController.postReview(eventDTO);
            eventController.postReview(eventDTO);
        }catch ( Exception e){
        }
        User user = userRepository.findById("3ede0ef2-92b7-4817-a5f3-0c575361f745").orElseThrow(Exception::new);
        Assertions.assertEquals(3,user.getPoint());
    }
}
