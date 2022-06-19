package com.triple.mileage.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Review {

    @Id
    private String id;
    private String userId;
    private int point;

}
