package com.triple.mileage.repository;

import com.triple.mileage.dao.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
    List<Photo> findAllByReviewId(String reviewId);

    void deleteById(String id);

    void deleteByIdIn(List<String> ids);
}
