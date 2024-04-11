package com.mv.recommender.lesson10;

import com.mv.recommender.lesson1.Filter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CollaborativeFilterNew implements Filter {
    public CollaborativeFilterNew() {
        System.out.println("collaborative filter NEW constructor called");
    }

    @Override
    public String[] getRecommendations(String movie) {
        return new String[] {"Finding Nemo", "Ice Age", "Toy Story" };
    }
}
