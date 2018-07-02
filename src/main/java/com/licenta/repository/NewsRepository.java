package com.licenta.repository;

import com.licenta.model.News;
import com.licenta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsRepository")
public interface NewsRepository  extends JpaRepository<News,Long> {
    News findById(int id);
    News findByTitle(String title);
    List<News> findAllByUser(User user);
}
