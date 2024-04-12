package com.mv.recommender.aop.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An aspect can be used to track the execution time of a method that intercepts calls to a particular layer.
 * Another approach could be to define a custom annotation on any method for which we want to track the execution time.
 * spring-aop allows us to create our own annotations and define aspects to implement them.
 *
 * We will restrict the use of this annotation to methods only. This can be achieved using the @Target annotation,
 * with ElementType set to METHOD. Other options include class, package, field, etc.
 *
 * We would also like the annotation information to be available at runtime.
 * We will use the @Retention annotation to define a retention policy.
 * Now that we have defined our annotation, we can add it to the JoinPointConfig file.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MeasureTime {
}
