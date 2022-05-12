package com.velcho.demo.controllers;

import com.velcho.demo.models.CurrentUser;
import com.velcho.demo.models.dto.UserLoginDTO;
import com.velcho.demo.models.dto.UserRegisterDTO;
import com.velcho.demo.services.UserService;
import com.velcho.demo.utill.FormObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller

public class RegisterController {


    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("register")
    public ModelAndView showRegister() {
        if(CurrentUser.getUser()!=null){
            CurrentUser.resetUser();
        }
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        ModelAndView modelAndView = new ModelAndView("user/register_form");
        modelAndView.addObject(FormObjects.REGISTER_FORM_OBJECT, userRegisterDTO);
        return modelAndView;
    }

    //to move information on invalid input need getters and setters
    @PostMapping(value = "register")
    public ModelAndView registerUser( @ModelAttribute( FormObjects.REGISTER_FORM_OBJECT) @Valid UserRegisterDTO userRegisterDTO,BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
          return new ModelAndView("user/register_form").addObject(FormObjects.REGISTER_FORM_OBJECT,userRegisterDTO);
        }

        this.userService.register(userRegisterDTO);

        return new ModelAndView("user/login").addObject(FormObjects.LOGIN_FORM_OBJECT,new UserLoginDTO());
    }
}
