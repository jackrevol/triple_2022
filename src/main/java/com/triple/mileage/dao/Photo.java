package com.triple.mileage.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Photo {

    @Id
    private String id;
    private String reviewId;


    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", reviewId='" + reviewId + '\'' +
                '}';
    }

}
