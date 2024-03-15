package com.example.backend.Classes;

import com.example.backend.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Data
@Entity
@Table(name = "user_table")
public class MyUser
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
    private String device_id;

    @Column(name = "role")
    private int role;


    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<HrEntry> hrEntries;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_class_table",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_id",referencedColumnName = "id")
    )
    private List<Subclass> classes;

    public MyUser( String name, String email, String password, String device_id, int role) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.device_id = device_id;
        this.role = role;
    }

    public MyUser( String name, String email, String password, String device_id, int role, int number, List<HrEntry> hrEntries, List<Subclass> classes) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.device_id = device_id;
        this.role = role;
        this.number = number;
        this.hrEntries = hrEntries;
        this.classes = classes;
    }

    


    public MyUser() {}

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


    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
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

    public UserDTO convertUserToUserDTO()
    {
        return new UserDTO(this.getName(),this.getEmail(),this.getPassword(),this.getDevice_id(),this.getRole());
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
