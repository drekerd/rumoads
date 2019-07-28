package com.example.categories;


import com.example.rumosAds.Adds;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity(name = "category")
public class CourseCategory {

    @Id
    private long categoryId;
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private Set<Adds> add;
}
