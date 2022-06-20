package com.triple.mileage.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Place {

    @Id
    private String id;
    private boolean hasReview;

    public Place(String placeId) {
        this.id = placeId;
        hasReview = false;
    }
}
