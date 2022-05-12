package com.velcho.demo.utill;

import com.velcho.demo.models.User;
import com.velcho.demo.models.dto.UserLoginDTO;
import com.velcho.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class LoginValidator implements ConstraintValidator<Login, Object> {

    private final UserRepository userRepository;

    @Autowired
    public LoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserLoginDTO userLoginDTO = (UserLoginDTO) obj;
        boolean isValid = false;

        Optional<User> optionalUser = this.userRepository.findByEmail(userLoginDTO.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(userLoginDTO.getPassword())) {
                isValid = true;
            }
        }

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( "wrongUser" ).addConstraintViolation();
        }
        return isValid;
    }
}
