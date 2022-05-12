package com.velcho.demo.controllers;

import com.velcho.demo.models.CurrentUser;
import com.velcho.demo.models.User;
import com.velcho.demo.models.dto.UserLoginDTO;
import com.velcho.demo.services.NotesService;
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
public class LoginController {



    private final UserService userService;
    private final NotesService notesService;


    @Autowired
    public LoginController(UserService userService, NotesService notesService) {
        this.userService = userService;
        this.notesService = notesService;
    }


    @GetMapping("login")
    public  ModelAndView showLogin(){
        if(CurrentUser.getUser()!=null){
            CurrentUser.resetUser();
        }

        ModelAndView modelAndView = new ModelAndView("user/login");
        UserLoginDTO userLoginDTO=new UserLoginDTO();
        modelAndView.addObject(FormObjects.LOGIN_FORM_OBJECT,userLoginDTO);
        return  modelAndView;
    }

    @PostMapping("login")
    public ModelAndView loginCheck(@ModelAttribute(FormObjects.LOGIN_FORM_OBJECT) @Valid UserLoginDTO userLoginDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ModelAndView("user/login").addObject(FormObjects.LOGIN_FORM_OBJECT,userLoginDTO);
        }

        User user = this.userService.findByEmail(userLoginDTO);
        CurrentUser.setUser(user);

        ModelAndView modelAndView = new ModelAndView("user/notes");
        modelAndView.addObject(FormObjects.NOTES_FORM_OBJECT, this.notesService.getNotes());
        modelAndView.addObject("loggedData", CurrentUser.getUser().getLoggedData());

        return modelAndView;
    }

}
