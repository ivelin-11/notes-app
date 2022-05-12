package com.velcho.demo.utill;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LoginValidator.class)//click to check
@Target( {ElementType.TYPE } )//type of anotation method,field or type for class(
//  when we compare two fileds)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    //error message
    public String message() default "Invalid email or password... Not recognize in the database";
    //represents group of constraints
    public Class<?>[] groups() default {};
    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
