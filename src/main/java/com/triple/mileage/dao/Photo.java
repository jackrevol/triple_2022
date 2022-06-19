package com.triple.mileage.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    private String id;
    @Column(nullable = false)
    private String reviewId;


    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", reviewId='" + reviewId + '\'' +
                '}';
    }

}
