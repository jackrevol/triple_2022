package com.triple.mileage.dto;

import com.triple.mileage.enums.EventAction;
import com.triple.mileage.enums.EventType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EventDTO {

    private EventType type;
    private EventAction action;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;

}
