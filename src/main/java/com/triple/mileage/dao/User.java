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
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private Long point;

    public User(String id) {
        this.id = id;
        this.point = 0L;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void subtractPoint(int point) {
        this.point -= point;
    }
}
