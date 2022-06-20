package com.triple.mileage.service;

import com.triple.mileage.dao.Photo;
import com.triple.mileage.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public Photo createPhoto(String photoId, String reviewId) {
        Photo photo = new Photo(photoId, reviewId);
        photoRepository.save(photo);
        return photo;
    }

    public void createPhotos(List<String> photoIdList, String reviewId) {
        List<Photo> photoList = new ArrayList();
        for(String photoId : photoIdList){
            photoList.add(new Photo(photoId, reviewId));
        }
        photoRepository.saveAll(photoList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAllPhotoRelateReview(String reviewId) {
        List<String> photoIdList = photoRepository.findAllByReviewId(reviewId)
                .stream().map(photo -> photo.getId())
                .collect(Collectors.toList());
        photoRepository.deleteByIdIn(photoIdList);
    }

    public void updatePhotosByModReview(List<String> photoIdList, String reviewId){
        List<String> orgPhotoIdList = photoRepository.findAllByReviewId(reviewId)
                .stream().map(photo -> photo.getId())
                .collect(Collectors.toList());

        Set<String> distinctPhotoList = orgPhotoIdList.stream()
                .distinct()
                .filter(photoIdList::contains)
                .collect(Collectors.toSet());

        orgPhotoIdList.removeAll(distinctPhotoList);
        photoIdList.removeAll(distinctPhotoList);
        photoRepository.deleteByIdIn(orgPhotoIdList);

        this.createPhotos(photoIdList,reviewId);
    }


}

