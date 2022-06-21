package com.triple.mileage.service;

import com.triple.mileage.dao.Log;
import com.triple.mileage.enums.EventAction;
import com.triple.mileage.enums.EventType;
import com.triple.mileage.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LogService {

    @Autowired
    LogRepository logRepository;

    public void logPointChange(String userId, int point, String reviewId, EventType eventType, EventAction eventAction) {
        Log log = new Log();
        log.setUserId(userId);
        log.setReviewId(reviewId);
        log.setPointChangeAmount(point);
        log.setEventType(eventType);
        log.setEventAction(eventAction);
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        logRepository.save(log);
    }

}
