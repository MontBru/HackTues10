package com.example.backend.auth;


import com.example.backend.DTO.SubClassDTO;
import com.example.backend.DTO.SubjectDTO;
import io.micrometer.common.lang.NonNull;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;

import java.util.LinkedList;
import java.util.List;

public class RegisterRequest {



//    @NotEmpty
//    @Size(min=2, max = 20, message = "user name should have at least 2 characters and max 20")
    private String username;


//    @NotEmpty
//    @Email
    private String email;


//    @NotEmpty
//    @Size(min = 6,  message = "user password should have at least 6 characters")
    private String password;


//    @Size(max = 500, message = "user description should have max 500 characters")
    private String device_id;

    private int number;

    private List<SubClassDTO> classes;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String email, String password, String device_id, int number, List<SubClassDTO> classes) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.device_id = device_id;
        this.number = number;
        this.classes = classes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<SubClassDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<SubClassDTO> classes) {
        this.classes = classes;
    }
}
