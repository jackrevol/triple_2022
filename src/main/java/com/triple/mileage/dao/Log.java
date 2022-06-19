package com.triple.mileage.dao;

import com.triple.mileage.dto.EventDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log {

    private String id;
    private int changePoint;
    private EventDTO event;

}
