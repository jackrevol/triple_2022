package com.triple.mileage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.triple.mileage.dto.EventDTO;
import com.triple.mileage.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/events")
    public ResponseEntity postReview(@RequestBody EventDTO eventDTO) throws JsonProcessingException {

        switch (eventDTO.getAction()) {
            case ADD: {
                reviewService.addReview(eventDTO);
                break;
            }
            case MOD: {
                reviewService.modReview(eventDTO);
                break;
            }
            case DELETE: {
                reviewService.deleteReview(eventDTO);
                break;
            }
        }
        return  new ResponseEntity("200 OK",HttpStatus.OK);
    }
}
