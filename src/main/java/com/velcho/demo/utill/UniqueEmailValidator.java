package com.velcho.demo.utill;

import com.velcho.demo.models.User;
import com.velcho.demo.models.dto.UserRegisterDTO;
import com.velcho.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {


    private final UserRepository userRepository;

    @Autowired
    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserRegisterDTO userRegisterDTO = (UserRegisterDTO) obj;
        boolean isValid=true;

            Optional<User> user = this.userRepository.findByEmail(userRegisterDTO.getEmail());
            if(user.isPresent()){
                isValid = false;
            }

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( "uniqueEmail" ).addConstraintViolation();
        }

        return isValid;
    }
}
