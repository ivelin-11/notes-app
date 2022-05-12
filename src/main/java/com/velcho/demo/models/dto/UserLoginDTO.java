package com.velcho.demo.models.dto;

import com.velcho.demo.utill.Login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Login
public class UserLoginDTO {


    @NotBlank(message = "Email must not be blank")
    @Email(regexp = "^(.+)@(.+)(\\.+)(.+)$",message = "Email must contain @ and at least one dot after it" )
    private String email;


    @NotNull(message = "Password must be not null")
    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;

    public UserLoginDTO(){}

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginDTO getWrongUser(){
        return new UserLoginDTO(this.email,this.password);
    }

    public void setWrongUser(UserLoginDTO userLoginDTO){
        this.email= userLoginDTO.getEmail();
        this.password=userLoginDTO.getPassword();
    }

}
