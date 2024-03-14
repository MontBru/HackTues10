package com.example.backend.auth;


import com.example.backend.Classes.MyUser;
import jdk.jfr.DataAmount;

public class AuthenticationResponse  {

    private String token;

    private MyUser user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, MyUser user) {
        this.token = token;
        this.user = user;

    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
