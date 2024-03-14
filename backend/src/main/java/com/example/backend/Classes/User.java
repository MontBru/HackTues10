package com.example.backend.Classes;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Optional;

@Data
@Builder
@Entity
@Table(name = "user_table")
public class User
{
    @Id
    @SequenceGenerator( name="user_sequence", sequenceName="user_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    private String name;

    private String email;

    private String password;

    private int device_id;

    private int role;

    private int number;

    public User(String name, String email, String password, int device_id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.device_id = device_id;

    }

    public User() {}

}
