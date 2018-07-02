package com.licenta.controller;


import com.licenta.model.News;

import java.util.ArrayList;
import java.util.List;

public class Feed {

    final String title;
    final String link;
    final String description;
    final String pubDate;

    final List<News> entries = new ArrayList<News>();

    public Feed(String title, String link, String description, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }

    public List<News> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }


    public String getPubDate() {
        return pubDate;
    }

    @Override
    public String toString() {
        return "Feed [copyright="  + ", description=" + description
                + ", language=" +  ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }

}