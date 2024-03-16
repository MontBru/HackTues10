package com.example.backend.auth;


import com.example.backend.Classes.MyUser;
import com.example.backend.DTO.MyUserDTO;
import jdk.jfr.DataAmount;

public class AuthenticationResponse  {

    private String token;

    private MyUserDTO user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, MyUserDTO user) {
        this.token = token;
        this.user = user;

    }

    public MyUserDTO getUser() {
        return user;
    }

    public void setUser(MyUserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
