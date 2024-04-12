package com.mv.recommender.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ExecutionTimeAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The parameter type of this method will be ProceedingJoinPoint instead of JoinPoint,
     * which we have used with methods marked with @Before and @After annotations.
     * ProceedingJoinPoint allows the continuation of the execution.
     * This method will return an Object that contains the values returned after the execution of the intercepted method call.
     * The proceed() method of ProceedingJoinPoint should either be surrounded by a try catch block
     * or should include a throws declaration with the method definition.
     *
     * We will use the @Around annotation to define a pointcut for method calls for which we want the execution time to be tracked.
     *
     */
    @Around("execution(* com.mv.recommender.aop.*.*(..))")
    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //note start time
        long startTime = System.currentTimeMillis();

        //allow method call to execute
        Object returnValue = joinPoint.proceed();

        //time taken = end time - start time
        long timeTaken = System.currentTimeMillis() - startTime;

        logger.info("Time taken by {} to complete execution is: {}", joinPoint, timeTaken);
        return returnValue;
    }

    @Around("execution(* com.mv.recommender.aop.*.*(..))")
    public Object calculateExecutionTimeWithCustomAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        return null;
    }

}