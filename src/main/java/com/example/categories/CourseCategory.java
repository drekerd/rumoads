package com.example.categories;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "category")
public class CourseCategory {

    @Id
    private int categoryId;
    private String categoryName;
}
