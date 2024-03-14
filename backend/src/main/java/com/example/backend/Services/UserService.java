package com.example.backend.Services;

import com.example.backend.DTO.UserDTO;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Classes.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    public List<UserDTO> getUsersDTO()
    {
            List<UserDTO> list_of_DTO = new ArrayList<>();
            List<MyUser> users = userRepository.findAll();
            for (MyUser user : users) {
                list_of_DTO.add(user.convertUserToUserDTO());
            }
            return list_of_DTO;
    }
}
