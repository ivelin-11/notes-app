package com.velcho.demo.utill;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConfirmPasswordValidator.class)//click to check
@Target( {ElementType.TYPE } )//type of anotation method,field or type for class(
//  when we compare two fileds)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
    //error message
    public String message() default "Your password and confirm password fields must be the same";
    //represents group of constraints
    public Class<?>[] groups() default {};
    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}