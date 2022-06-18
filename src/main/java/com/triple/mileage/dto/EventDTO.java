package com.triple.mileage.dto;

import com.triple.mileage.enums.EventAction;
import com.triple.mileage.enums.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventDTO {

    private EventAction type;
    private EventType action;
    private String reviewID;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;

}
