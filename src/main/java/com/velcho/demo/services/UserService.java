package com.velcho.demo.services;


import com.velcho.demo.models.User;
import com.velcho.demo.models.dto.UserLoginDTO;
import com.velcho.demo.models.dto.UserRegisterDTO;
import com.velcho.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    <T> User findByEmail(T user);

    void save(User user);
}
