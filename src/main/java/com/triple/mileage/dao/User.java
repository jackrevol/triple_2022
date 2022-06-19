package com.triple.mileage.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class User {

    @Id
    private String id;
    private Long point;

}
