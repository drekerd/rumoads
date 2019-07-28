package com.example.dao;

import com.example.rumosAds.Adds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CoursesDAO extends JpaRepository<Adds, Long> {

    List<Adds> findByCategoryCategoryId(long categoryId);

}
