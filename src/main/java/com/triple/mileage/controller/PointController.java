package com.triple.mileage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.triple.mileage.dto.PointDTO;
import com.triple.mileage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {

    @Autowired
    UserService userService;

    @GetMapping("/point")
    public Long getUserPoint(@RequestBody PointDTO pointDTO) throws JsonProcessingException {
        return userService.getUserPoint(pointDTO.getUserId());
    }

}
