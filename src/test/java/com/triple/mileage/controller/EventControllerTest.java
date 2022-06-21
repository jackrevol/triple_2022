package com.triple.mileage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventControllerTest {

    @Autowired
    EventController eventController;

    @Test
    @Order(001)
    public void addTest() throws JsonProcessingException {
        String input = "{\n" +
                "\"type\": \"REVIEW\",\n" +
                "\"action\": \"ADD\", \n" +
                "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
                "\"content\": \"좋아요!\",\n" +
                "\"attachedPhotoIds\": [\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\", \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"],\n" +
                "\"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
                "\"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
                "}";

        eventController.postReview(input);
    }


    @Test
    @Order(002)
    public void modTest() throws JsonProcessingException {
        String input = "{\n" +
                "\"type\": \"REVIEW\",\n" +
                "\"action\": \"MOD\", \n" +
                "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
                "\"content\": \"\",\n" +
                "\"attachedPhotoIds\": [],\n" +
                "\"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
                "\"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
                "}";

        eventController.postReview(input);
    }


    @Test
    @Order(003)
    public void deleteTest() throws JsonProcessingException {
        String input = "{\n" +
                "\"type\": \"REVIEW\",\n" +
                "\"action\": \"DELETE\", \n" +
                "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
                "\"content\": \"좋아요!\",\n" +
                "\"attachedPhotoIds\": [],\n" +
                "\"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
                "\"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
                "}";

        eventController.postReview(input);
    }
}
