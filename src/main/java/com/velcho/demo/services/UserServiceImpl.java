package com.velcho.demo.services;

import com.velcho.demo.models.User;
import com.velcho.demo.models.dto.UserLoginDTO;
import com.velcho.demo.models.dto.UserRegisterDTO;
import com.velcho.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        this.userRepository.save(user);
    }

    public void save(User user){
        this.userRepository.save(user);
    }


    //Input UserRegisterDTO or User
    public   <T> User    findByEmail(T user){
        if(user instanceof  User){
            User map = this.modelMapper.map(user, User.class);
            return
                    this.userRepository.findByEmail(map.getEmail())
                            .get();
        }
        else if(user instanceof UserLoginDTO){
            UserLoginDTO map = this.modelMapper.map(user, UserLoginDTO.class);
            return
                    this.userRepository.findByEmail(map.getEmail())
                            .get();
        }
       return null;
    }


}
