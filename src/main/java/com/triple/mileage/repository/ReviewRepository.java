package com.triple.mileage.repository;

import com.triple.mileage.dao.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    boolean existsByPlaceId(String placeId);

    Optional<Review> findById(String id);

    void deleteById(String id);
}
