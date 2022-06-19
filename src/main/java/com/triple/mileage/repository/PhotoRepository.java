package com.triple.mileage.repository;

import com.triple.mileage.dao.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,String> {

}
