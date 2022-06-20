package com.triple.mileage.service;

import com.triple.mileage.dao.Place;
import com.triple.mileage.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    public Place createPlace(String placeId) {
        Place place = new Place(placeId);
        placeRepository.save(place);
        return place;
    }

    public boolean hasReview(String placeId) {
        return placeRepository.findById(placeId)
                .orElse(this.createPlace(placeId))
                .isHasReview();
    }

    public void makeHasNoReview(String placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(NullPointerException::new);
        place.setHasReview(false);
        placeRepository.save(place);
    }
}
