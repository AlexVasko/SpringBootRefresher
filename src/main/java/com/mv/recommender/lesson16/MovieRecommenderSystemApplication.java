package com.mv.recommender.lesson16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@SpringBootApplication
@PropertySource("classpath:app.properties")
public class MovieRecommenderSystemApplication {
    public static void main(String[] args) {
        ApplicationContext appContext = SpringApplication.run(
                MovieRecommenderSystemApplication.class, args);
        RecommenderImplementation recommender = appContext.getBean(RecommenderImplementation.class);
        ContentBasedFilter filter = appContext.getBean(ContentBasedFilter.class);
        System.out.println("\nContentBasedFilter bean with singleton scope");
        String favoriteMovie = recommender.getFavoriteMovie();
        System.out.println(favoriteMovie);

        System.out.println(Arrays.toString(filter.getRecommendations(favoriteMovie)));

    }
}

