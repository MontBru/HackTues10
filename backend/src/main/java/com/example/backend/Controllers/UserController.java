package com.example.backend.Controllers;

import com.example.backend.DTO.UserDTO;
import com.example.backend.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getEvaluation/{id}/{flag}")
    public int  getEvaluation(@PathVariable Long id, @PathVariable int flag){
        return userService.getEvaluationAVG(id,flag);
    }

    @GetMapping("/isTokenValid")
    public String isAuthenticated(HttpServletResponse response, HttpServletRequest request){
        boolean isAuthenticated = (boolean) request.getAttribute("isAuthenticated");
        if(!isAuthenticated)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "UnAuthorized";
        }

        return "Success";
    }

}
