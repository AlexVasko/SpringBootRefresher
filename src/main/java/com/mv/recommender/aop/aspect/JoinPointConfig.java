package com.mv.recommender.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class JoinPointConfig {

    @Pointcut("execution(* com.mv.recommender.aop.*.*(..))")
    public void dataLayerPointcut() {
    }

    @Pointcut("execution(* com.mv.recommender.aop.*.*(..))")
    public void businessLayerPointcut() {}

    /*Previously, we had been using pointcuts as follows:
    @AfterReturning("execution(* com.mv.recommender.aop.*.*(..))")
    public void logAfterExecution(JoinPoint joinPoint) {
        //...
    }*/

    //Now, we will use the method that defines this pointcut in the configuration file as follows:
    /*
    @AfterReturning("com.mv.recommender.aop.aspect.JoinPointConfig.dataLayerPointcut()")
        public void logAfterExecution(JoinPoint joinPoint) {
    //...
}
     */

    /**
     * For multiple layers
     * We can also combine pointcuts using the AND (&&), (OR) ||, and (NOT) ! operators.
     * The method allLayerPointcut() will intercept calls belonging to either the business layer or the data layer.
     */
    @Pointcut("com.mv.recommender.aop.aspect.JoinPointConfig.dataLayerPointcut() || "
            + "com.mv.recommender.aop.aspect.JoinPointConfig.businessLayerPointcut()")
    public void allLayersPointcut() {}

    /**
     * For a bean
     * We can also define a pointcut to intercept calls belonging to a particular bean.
     * Say we want to log the execution of all methods belonging to beans that have the word movie in their name.
     * We can define a pointcut as follows:
     *
     * @Pointcut("bean(movie*)")
     * 	    public void movieBeanPointcut() {}
     */


    /**
     * We can now use this pointcut to calculate the execution time of only chosen methods.
     */
    @Pointcut("@annotation(com.mv.recommender.aop.aspect.MeasureTime)")
    public void measureTimeAnnotation() {}
}
