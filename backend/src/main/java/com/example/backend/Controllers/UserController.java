package com.example.backend.Controllers;

import com.example.backend.DTO.UserDTO;
import com.example.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public List<UserDTO> getUsers()
    {
        return userService.getUsersDTO();
    }

    @GetMapping("/getEvaluation/{id}/{time}")
    public int  getEvaluation(@RequestParam Long id, @RequestParam int flag){
        return userService.getEvaluationAVG(id,flag);
    }

}
