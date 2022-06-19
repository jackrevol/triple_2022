package com.triple.mileage.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Place {

    @Id
    private String id;
    private boolean hasReview;

}
