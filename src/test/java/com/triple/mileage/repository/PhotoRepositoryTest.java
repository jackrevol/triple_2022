package com.triple.mileage.repository;

import com.triple.mileage.dao.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class PhotoRepositoryTest {

    @Autowired
    PhotoRepository photoRepository;

    @Test
    void save(){
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setReviewId(UUID.randomUUID().toString());
        System.out.println(photo.toString());
        photoRepository.save(photo);
    }
}
