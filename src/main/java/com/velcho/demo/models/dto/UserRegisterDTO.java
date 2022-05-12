package com.velcho.demo.models.dto;

import com.velcho.demo.utill.ConfirmPassword;
import com.velcho.demo.utill.UniqueEmail;

import javax.validation.constraints.*;

@ConfirmPassword
@UniqueEmail
public class UserRegisterDTO {

    @Size(min = 3, message = "First Name size must be min 3")
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @Size(min = 3, message = "Last Name size must be min 3")
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    @Email(regexp = "^(.+)@(.+)(\\.+)(.+)$",message = "Email must contain @ and at least one dot after it" )
    private String email;

    @NotNull(message = "Password must be not null")
    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Password must be not null")
    private String confirmPassword;

    @Positive(message = "Age must be a positive number")
    @Min(value=18, message="Your age must be equal or greater than 18")
    private int age;

    @NotBlank(message = "Gender must not be blank")
    private String gender;

    public UserRegisterDTO(){}


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    //get for the unique email validation
    public String getUniqueEmail(){
        return this.email;
    }

    //set for the unique email validation
    public void setUniqueEmail(String email){
      this.email=email;
    }
}
