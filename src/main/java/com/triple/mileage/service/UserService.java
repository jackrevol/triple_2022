package com.triple.mileage.service;

import com.triple.mileage.dao.User;
import com.triple.mileage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public Long getUserPoint(String userId) {
        return userRepository.findById(userId).orElseThrow(NullPointerException::new).getPoint();
    }

    public void addUserPoint(String userId, int point) {
        User user = userRepository.findById(userId).orElse(new User(userId));
        user.addPoint(point);
        userRepository.save(user);
    }

    public void subtractUserPoint(String userId, int point) {
        User user = userRepository.findById(userId).orElse(new User(userId));
        user.subtractPoint(point);
        userRepository.save(user);
    }

    public void modUserPoint(String userId, int reviewOrgPoint, int reviewModPoint) {
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        user.setPoint(user.getPoint() - reviewOrgPoint + reviewModPoint);
        userRepository.save(user);
    }
}
