package com.velcho.demo.utill;

import com.velcho.demo.models.dto.UserRegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator  implements ConstraintValidator<ConfirmPassword, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserRegisterDTO user = (UserRegisterDTO) obj;
        boolean isValid=true;
        if(user.getPassword() == null || user.getConfirmPassword() == null){
           isValid=false;
        }
       else  {
            isValid = user.getPassword().equals(user.getConfirmPassword());
        }

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( "confirmPassword" ).addConstraintViolation();
        }
        return  isValid;
    }


}
