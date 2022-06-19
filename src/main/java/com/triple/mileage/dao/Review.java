package com.triple.mileage.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    private String id;
    private String userId;
    private String placeId;
    private String content;
    private boolean isFirstReview;
    private int point;

}
