package com.mv.recommender.lesson1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CollaborativeFilter implements Filter{
    public CollaborativeFilter() {
        System.out.println("collaborative filter constructor called");
    }

    @Override
    public String[] getRecommendations(String movie) {
        return new String[] {"Finding Nemo", "Ice Age", "Toy Story" };
    }
}
