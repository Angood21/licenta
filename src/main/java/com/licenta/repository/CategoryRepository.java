package com.licenta.repository;


import com.licenta.model.Category;
import com.licenta.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("CategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategory(String category);
    Category findById(int id);
}
