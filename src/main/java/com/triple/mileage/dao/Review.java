package com.triple.mileage.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "review", columnList = "userId"),
        @Index(name = "place", columnList = "placeId")
})
public class Review {

    @Id
    private String id;
    private String userId;
    private String placeId;
    private String content;
    private boolean isFirstReview;
    private int point;

}
