package com.triple.mileage.log;

import com.triple.mileage.enums.EventAction;
import com.triple.mileage.enums.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PointChangeLog {

    @Id
    private String userId;
    private String reviewId;
    private EventAction eventAction;
    private EventType eventType;
    private int pointChangeAmount;
    public PointChangeLog(String userId, String reviewId, EventAction eventAction, EventType eventType, int pointChangeAmount){
        this.userId = userId;
        this.reviewId = reviewId;
        this.eventAction = eventAction;
        this.eventType = eventType;
        this.pointChangeAmount = pointChangeAmount;
    }

    @Override
    public String toString() {
        return "PointChangLog{" +
                "userId='" + userId + '\'' +
                ", reviewId='" + reviewId + '\'' +
                ", eventAction=" + eventAction +
                ", eventType=" + eventType +
                ", pointChangeAmount=" + pointChangeAmount +
                '}';
    }
}
