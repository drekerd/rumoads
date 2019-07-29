package com.example.dao;

import com.example.categories.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<CourseCategory, Long> {

}
