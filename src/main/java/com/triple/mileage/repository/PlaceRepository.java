package com.triple.mileage.repository;

import com.triple.mileage.dao.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {

    Optional<Place> findById(String id);
}
