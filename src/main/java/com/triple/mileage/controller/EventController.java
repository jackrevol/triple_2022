package com.triple.mileage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {


    @PostMapping("/events")
    public Long postReview(@RequestBody String requestBody){


        return  0L;
    }
}
