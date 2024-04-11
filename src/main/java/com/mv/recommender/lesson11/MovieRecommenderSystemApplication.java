package com.mv.recommender.lesson11;

import com.mv.recommender.lesson1.Movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mv.recommender.lesson10","com.mv.recommender.lesson11"})
public class MovieRecommenderSystemApplication {
    public static void main(String[] args) {
        ApplicationContext appContext = SpringApplication.run(
                MovieRecommenderSystemApplication.class, args);
        ContentBasedFilter filter = appContext.getBean(ContentBasedFilter.class);
        System.out.println("\nContentBasedFilter bean with singleton scope");
        System.out.println(filter);

        System.out.println(filter.getMovie());
        System.out.println(filter.getMovie());

    }
}

