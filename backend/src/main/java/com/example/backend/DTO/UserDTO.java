package com.example.backend.DTO;

import jakarta.persistence.Column;

public class UserDTO
{
    private String name;
    private String email;
    private String password;
    private int device_id;
    private int role;

    public UserDTO(String name, String email, String password, int device_id, int role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.device_id = device_id;
        this.role = role;
    }

    public UserDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
