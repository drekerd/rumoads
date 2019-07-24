package com.example.categories;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CategoryService {

    List<CourseCategory> categories;

}
