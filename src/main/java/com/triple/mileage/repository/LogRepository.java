package com.triple.mileage.repository;

import com.triple.mileage.dao.Log;
import com.triple.mileage.dao.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {

}
