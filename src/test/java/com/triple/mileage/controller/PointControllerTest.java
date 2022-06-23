package com.triple.mileage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.triple.mileage.dto.PointDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PointControllerTest {

    @Autowired
    PointController pointController;

    @Test
    @Order(004)
    public void addTest() throws JsonProcessingException {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setUserId("3ede0ef2-92b7-4817-a5f3-0c575361f745");
        Long t = pointController.getUserPoint(pointDTO);
        Assertions.assertEquals(0, t);
    }
}
