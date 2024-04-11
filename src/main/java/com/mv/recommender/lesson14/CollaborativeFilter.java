package com.mv.recommender.lesson14;

import org.springframework.stereotype.Component;

@Component
public class CollaborativeFilter implements Filter {
    public CollaborativeFilter() {
        System.out.println("collaborative filter constructor called");
    }

    @Override
    public String[] getRecommendations(String movie) {
        return new String[] {"Finding Nemo", "Ice Age", "Toy Story" };
    }
}
