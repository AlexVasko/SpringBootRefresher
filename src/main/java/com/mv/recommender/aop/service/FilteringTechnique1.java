package com.mv.recommender.aop.service;

import com.mv.recommender.aop.aspect.MeasureTime;
import com.mv.recommender.aop.dao.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilteringTechnique1 {
    @Autowired
    private Movie movie;

    @MeasureTime
    public String contentBasedFiltering() {
        return movie.getMovieDetails();
    }
}
