package com.openclassrooms.watchlist.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
//todo specifies annotation is valid at run time
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= PriorityValidator.class)
public @interface Priority {

    String message() default "Please enter M,L or H for priority";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
