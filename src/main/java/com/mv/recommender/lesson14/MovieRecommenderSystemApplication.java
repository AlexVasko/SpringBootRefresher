package com.mv.recommender.lesson14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

//@SpringBootApplication
@ComponentScan
@Configuration
public class MovieRecommenderSystemApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("appContext.xml");
        System.out.println("\nBeans loaded:");
        System.out.println(Arrays.toString(appContext.getBeanDefinitionNames()));

        RecommenderImplementation recommender =
                appContext.getBean("recommenderImpl", RecommenderImplementation.class);

        System.out.println("\nDependency: " + recommender.getFilter());
        System.out.println();

        appContext.close();

    }
}

