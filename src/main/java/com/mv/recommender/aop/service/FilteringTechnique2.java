package com.mv.recommender.aop.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.mv.recommender.aop.dao.User;

@Service
public class FilteringTechnique2 {
    @Autowired
    private User user;

    public String collaborativeFiltering() {
        return user.getUserDetails();
    }
}
