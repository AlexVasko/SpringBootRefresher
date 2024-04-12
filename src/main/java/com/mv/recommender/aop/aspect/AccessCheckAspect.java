package com.mv.recommender.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AccessCheckAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Advice - the method that contains the logic of the steps
     * that need to be carried out when a method call gets intercepted.
     *
     * @Before needs an argument which specifies the method calls that will be intercepted.
     * This is called the pointcut. Pointcuts are defined in the following format:
     * execution(* PACKAGE.*.*(..))
     * The pointcut expression starts with a key word called a designator, which tells Spring AOP what to match.
     * execution is the primary designator which matches method execution joinpoints.
     * <p>
     * The first * in the expression corresponds to the return type. * means any return type.
     * Then comes the package name followed by class and method names.
     * The first * after package means any class and the second * means any method. Instead of *,
     * we could specify the class name and method name to make the pointcut expression specific.
     * Lastly, parentheses correspond to arguments. (..) means any kind of argument.
     * The userAccess() method is invoked before the actual method.
     * This method contains the logic for checking user access, which is not shown.
     * <p>
     * Joinpoint
     * To find out which method calls have been intercepted, we will use a join point as an argument to the method.
     * The joinpoint contains the name of the method that is intercepted.
     * We can use the joinpoint to print the name of the method as follows:
     * <p>
     * <p>
     * If we remove the name of the package (business), the pointcut expression becomes:
     * @Before("execution(* io.datajek.springaop.movierecommenderaop..*.*(..))")
     * This will intercept all calls in the package.
     * Say we want to intercept calls to all methods that return a String value.
     * This can be done by specifying String as the return type in the pointcut expression as follows:
     * @Before("execution(String com.mv.recommender.aop..*.*(..))")
     * Here we are looking at calls in all subpackages of the ...recommender.aop package and matching the return type of the method calls to String.
     * Intercepting calls to a specific method
     * If we want to intercept calls to all methods that have the word Filtering in it, we will use the following pointcut expression
     * @Before("execution(String io.datajek.springaop.movierecommenderaop..*.*Filtering(..))")
     * The wildcard * used in place of the method name will match all methods that have the word Filtering in it.
     * Calls to contentBasedFiltering() and collaborativeFiltering() will be intercepted.
     * Intercepting calls with specific method arguments.
     * @Before("execution(* com.mv.recommender.aop.service.*.*(String))")
     * This pointcut will match method calls having one parameter of String type. We can modify this expression to match all method calls with String as the first argument as follows:
     * @Before("execution(*io.datajek.springaop.movierecommenderaop..*.*(String,..))")
     * Combining pointcut expressions
     * The && , || and ! symbols can be used to combine different pointcut expressions.
     * @Before("execution(* com.mv.recommender.aop.service.*.*Filtering(..)) ||
     * execution(String com.mv.recommender.aop.service.*.*(..))")
     **/
    @Before("execution(* com.mv.recommender.aop.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        //	logger.info("Intercepted method call");

        logger.info("Intercepted call before execution of: {}", joinPoint);

        //access check logic
    }
}