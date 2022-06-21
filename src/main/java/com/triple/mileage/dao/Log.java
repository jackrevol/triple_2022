package com.triple.mileage.dao;

import com.triple.mileage.dto.EventDTO;
import com.triple.mileage.enums.EventAction;
import com.triple.mileage.enums.EventType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "user", columnList = "userId")
})
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long key;
    private String userId;
    private String reviewId;
    private EventAction eventAction;
    private EventType eventType;
    private int pointChangeAmount;
    private Timestamp timestamp;

}
