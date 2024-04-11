package com.mv.recommender.lesson1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mv.recommender.lesson10"})
@ComponentScan(includeFilters = @ComponentScan.Filter (
        type= FilterType.REGEX,
        pattern="com.mv.recommender.lesson1.*"))
public class MovieRecommenderSystemApplication {
    public static void main(String[] args) {
        ApplicationContext appContext = SpringApplication.run(
                MovieRecommenderSystemApplication.class, args);
        ContentBasedFilter filter = appContext.getBean(ContentBasedFilter.class);
        System.out.println("\nContentBasedFilter bean with singleton scope");
        System.out.println(filter);

        System.out.println("ContentBasedFilter bean found = " + appContext.containsBean("contentBasedFilter"));
        System.out.println("CollaborativeFilter bean found = " + appContext.containsBean("collaborativeFilter"));

        System.out.println("\nMovie bean with prototype scope");
        System.out.println(filter.getMovie());
        System.out.println(filter.getMovie());
        System.out.println(filter.getMovie());

        //Print number of instances of each bean
        System.out.println("\nContentBasedFilter instances created: " + ContentBasedFilter.getInstances());
        System.out.println("Movie instances created: "+ Movie.getInstances());
    }
}

