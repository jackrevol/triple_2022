package com.triple.mileage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.mileage.dao.User;
import com.triple.mileage.dto.PointDTO;
import com.triple.mileage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/point")
    public Long getUserPoint(@RequestBody String requestBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PointDTO pointDTO = objectMapper.readValue(requestBody, PointDTO.class);
        User user = userRepository.findById(pointDTO.getUserId()).orElseThrow(NullPointerException::new);
        return user.getPoint();
    }

}
