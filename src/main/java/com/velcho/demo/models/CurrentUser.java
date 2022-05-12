package com.velcho.demo.models;

import com.velcho.demo.models.dto.UserLoginDTO;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class CurrentUser {
    private static User user;
    private static final ModelMapper modelMapper = new ModelMapper();



    public static User getUser() {
        return user;
    }

    public static void resetUser() {
        user = null;
    }

    public static void setUser(User _user) {
        user = _user;
    }
}
