package com.mv.recommender.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * To tell spring-aop that this method needs to be called after the intercepted method call is executed,
     * we will use the @AfterReturning annotation.
     * We can get the return values of the method here by using the returning tag. Now that pointcut is not the only argument
     * in the @AfterReturning annotation, we will use tags to differentiate arguments.
     * value contains the pointcut expression and returning contains the value that is returned by the executing method,
     * which is stored in result and passed to the LogAfterExecution method.
     * Note that result is of type Object because different methods will have different return types.
     */

    @AfterReturning(
            value = "execution(* com.mv.recommender.aop.*.*(..))",
            returning = "result")
    public void LogAfterExecution(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned with: {}", joinPoint, result);
    }


    /**
     * @AfterThrowing annotation
     * To intercept an exception, another annotation,
     * @AfterThrowing, is used. We can get the result of the exception using the throwing tag.
     */
    @AfterThrowing(
            value = "execution(* com.mv.recommender.aop.*.*(..))",
            throwing = "exception")
    public void LogAfterException(JoinPoint joinPoint, Throwable exception) {
        logger.info("Method {} returned with: {}", joinPoint, exception.getMessage());
    }

    /**
     * @After annotation
     * The @After annotation is a generic annotation that is used in both scenarios, whether the method execution
     * is successful or results in an exception. The method LogAfterMethod() demonstrates the use of this annotation.
     */
    @After("execution(* com.mv.recommender.aop.*.*(..))")
    public void LogAfterMethod(JoinPoint joinPoint) {
        logger.info("After method call {}", joinPoint);
    }

}
