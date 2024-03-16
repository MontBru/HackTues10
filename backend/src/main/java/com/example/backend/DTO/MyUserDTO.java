package com.example.backend.DTO;

public class MyUserDTO
{
    private String name;

    private int role;


    public MyUserDTO(String name, int role) {
        this.name = name;
        this.role = role;
    }


    public MyUserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
