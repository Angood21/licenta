package com.licenta;

import com.licenta.model.Category;
import com.licenta.repository.CategoryRepository;
import com.licenta.repository.NewsRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.Query;

@SpringBootApplication
public class NewsAggregatorApplication {

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(NewsAggregatorApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase(){
        return()->{

        };

    }

}
