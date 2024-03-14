package com.example.backend.Classes;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@Entity
@Table(name = "user_table")
public class User
{
    @Id
    @SequenceGenerator( name="user_sequence", sequenceName="user_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "device_id")
    private int device_id;

    @Column(name = "role")
    private int role;


    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<HrEntry> hrEntries;

    @OneToMany(mappedBy="user_class", fetch = FetchType.EAGER)
    private List<Subclass> classes;

    public User(Long id, String name, String email, String password, int device_id, int role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.device_id = device_id;
        this.role = role;
    }

    public User(Long id, String name, String email, String password, int device_id, int role, int number, List<HrEntry> hrEntries, List<Subclass> classes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.device_id = device_id;
        this.role = role;
        this.number = number;
        this.hrEntries = hrEntries;
        this.classes = classes;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<HrEntry> getHrEntries() {
        return hrEntries;
    }

    public void setHrEntries(List<HrEntry> hrEntries) {
        this.hrEntries = hrEntries;
    }

    public List<Subclass> getClasses() {
        return classes;
    }

    public void setClasses(List<Subclass> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", device_id=" + device_id +
                ", role=" + role +
                ", number=" + number +
                '}';
    }
}
