package com.licenta.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_category", updatable = false, nullable = false)
    private Long id;

    @Column(name = "category")
    private String category;

    public Category(String category) {
        this.category = category;
    }

    public Category() {

    }

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Source> sources = new ArrayList<>();



    public void addSource(Source currentSource) {
        sources.add(currentSource);
        currentSource.setCategory(this);
    }

    public void removeSource(Source currentSource) {
        sources.remove(currentSource);
        currentSource.setCategory(null);
    }

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<News> news = new ArrayList<>();

    public void addNews(News currentNews) {
        news.add(currentNews);
        currentNews.setCategory(this);
    }

    public void removeNews(News currentNews) {
        news.remove(currentNews);
        currentNews.setCategory(null);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
