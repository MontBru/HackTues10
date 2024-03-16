package com.example.backend.Services;

import com.example.backend.DTO.UserDTO;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Classes.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
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

    public int getEvaluationAVG(Long id,int flag)
    {
        MyUser user = userRepository.findById(id).orElse(null);
        if(user.getHrEntries().size() != 0 ) {
            switch (flag) {
                case 0:
                    LocalDateTime date1 = LocalDateTime.now().minusDays(1);
                    return userRepository.getEvaluationAVGDate(id, date1);
                case 1:
                    LocalDateTime date2 = LocalDateTime.now().minusWeeks(1);
                    return userRepository.getEvaluationAVGDate(id, date2);
                default:
                    LocalDateTime date3 = LocalDateTime.now().minusMonths(1);
                    return userRepository.getEvaluationAVGDate(id, date3);
            }
        }
        else
            return 0;
    }

    public Optional<Object> getByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }
}
