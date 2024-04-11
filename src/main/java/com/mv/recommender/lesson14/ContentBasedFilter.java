package com.mv.recommender.lesson14;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("contentBasedFilter")
public class ContentBasedFilter implements Filter {

    private static int instances= 0;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Movie movie;

    @Autowired
    public ContentBasedFilter(Movie movie) {
        this.movie = movie;
        instances++;
        logger.info("In ContentBasedFilter constructor method");
    }

//    @Lookup
    public Movie getMovie() {
        return movie;
    }

    public static int getInstances(){
        return ContentBasedFilter.instances;
    }

    @Override
    public String[] getRecommendations(String movie) {
        //logic of content based filter
        return new String[] {"Happy Feet", "Ice Age", "Shark Tale"};
    }

    @PostConstruct
    public void postConstruct() {
        //initialization code goes here
        logger.info("In RecommenderImplementation postConstruct method");
    }

    @PreDestroy
    public void preDestroy() {
        //cleanup code
        logger.info("In RecommenderImplementation preDestroy method");
    }
}