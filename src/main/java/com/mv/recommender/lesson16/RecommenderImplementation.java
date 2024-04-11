package com.mv.recommender.lesson16;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RecommenderImplementation {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Filter filter;
    @Value("${recommender.implementation.favoriteMovie}")
    private String favoriteMovie = "Finding Dory";


    public RecommenderImplementation(Filter contentBasedFilter) {
        super();
        this.filter = contentBasedFilter;
        System.out.println("Constructor invoked...");
    }


    public String getFavoriteMovie() {
        return favoriteMovie;
    }

    public String[] recommendMovies(String movie) {
        System.out.println("Name of the filter in use: " + filter + "\n");
        return filter.getRecommendations(movie);
    }

    @Autowired
    public void setFilter(Filter filter) {
        logger.info("In RecommenderImplementation setter method..dependency injection");
        this.filter = filter;
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("In RecommenderImplementation postConstruct method");
    }

    @PreDestroy
    public void preDestroy() {
        //cleanup code
        logger.info("In RecommenderImplementation preDestroy method");
    }
}