package com.licenta.repository;

import com.licenta.model.Category;
import com.licenta.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SourceRepository")
public interface SourceRepository extends JpaRepository<Source, Long> {
    Source findByUrl(String url);

    List<Source> findByCategory(Category category);

    List<Source> findAll();
}
