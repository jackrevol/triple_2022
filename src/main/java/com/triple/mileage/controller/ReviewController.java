package com.triple.mileage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.mileage.dto.EventDTO;
import com.triple.mileage.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/events")
    public Long postReview(@RequestBody String requestBody) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        EventDTO eventDTO = mapper.readValue(requestBody, EventDTO.class);

        switch (eventDTO.getAction()) {
            case ADD: {
                reviewService.addReview(eventDTO);
            }
            case MOD: {
                reviewService.modReview(eventDTO);
            }
            case DELETE: {
                reviewService.deleteReview(eventDTO);
            }
        }
        return 0L;
    }
}
