package com.triple.mileage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventDTO {

    private String type;
    private String action;
    private String reviewID;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;

}
