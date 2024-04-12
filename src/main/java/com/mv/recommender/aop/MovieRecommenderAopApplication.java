package com.mv.recommender.aop;

import com.mv.recommender.aop.service.FilteringTechnique1;
import com.mv.recommender.aop.service.FilteringTechnique2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CommandLineRunner is used when we want to execute some code right after the context is loaded
 * and as soon as the startup completes.
 * Aspect
 * An aspect is a Java class that implements cross-cutting concerns.
 * The @Aspect annotation is used to define a class as an aspect.
 * An aspect is a combination of the kinds of methods to intercept and what to do after intercepting them.
 * Aspects can define functionality for any concern like logging, performance management,
 * or transaction management that cuts across multiple application layers.
 *
 * Pointcut
 * Pointcuts are expressions that determine which method calls will be intercepted.
 * These expressions determine whether an advice needs to be executed or not.
 * Pointcuts are defined following a particular syntax.
 * Pointcuts should be carefully defined, as they determine how many calls will be intercepted.
 *
 * Advice
 * The tasks performed after intercepting a method call are called advice.
 * It is the logic of the methods in a class marked with @Aspect.
 * Advices are basically the methods that get executed when a method calls meets a pointcut.
 * These methods can get executed before, around the time of, or after the execution of the intercepted method call.
 * There are different advice types as shown below.
 * Before, After, After Running, After Throwing, Around
 *
 * Joinpoint
 * All method calls that are intercepted are joinpoints. It is a point in the program execution where an aspect
 * can be plugged in. It contains the name of the intercepted method call.
 *
 * Weaving
 * The process of implementing AOP around method calls is called weaving.
 * Weaving links an aspect with objects in the application to create an advised object.
 * The aspect is called at the right moment. For example,
 * if we are tracking the execution time of some methods in our application, the weaving process will be like this:
 *
 *            Business Logic         Tracking Time Concerns
 *                 |                       |
 *                 |                       |
 *                 ------------------------
 *                            |
 *                    Tracking Time Concern
 *                       Business Logic
 *                   Tracking Time Concern
 *
 *  Weaver
 * The framework that ensures that an aspect is invoked at the right time is called a weaver.
 */
@SpringBootApplication
public class MovieRecommenderAopApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FilteringTechnique1 filter1;

    @Autowired
    private FilteringTechnique2 filter2;

    public static void main(String[] args) {
        SpringApplication.run(MovieRecommenderAopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("{}",filter1.contentBasedFiltering());
        logger.info("{}",filter2.collaborativeFiltering());
    }
}
