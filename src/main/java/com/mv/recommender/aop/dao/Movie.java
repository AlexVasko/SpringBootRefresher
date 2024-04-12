package com.mv.recommender.aop.dao;

import org.springframework.stereotype.Repository;

@Repository
public class Movie {
    public String getMovieDetails() {
        //interacts with the Movie repository
        return "movie details";
    }
}