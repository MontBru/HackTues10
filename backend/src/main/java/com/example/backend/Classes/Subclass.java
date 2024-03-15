package com.example.backend.Classes;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Entity
@Table(name = "subclass_table")
public class Subclass
{
    @Id
    @SequenceGenerator( name="subclass_sequence", sequenceName="subclass_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "subclass_sequence")
    private Long id;

    @Column(name = "klas")
    private String klas;

    @Column(name = "grade")
    private int grade;

    @ManyToOne
    @JsonManagedReference
    private MyUser user_class;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "class_user_table",
            joinColumns = @JoinColumn(name = "class_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id")
    )
    private List<MyUser> users;

    @OneToMany(mappedBy="class_subjects", fetch = FetchType.EAGER)
    private List<Subject> subjects;

    public Subclass() {}

    public Subclass(Long id, String klas, int grade) {
        this.id = id;
        this.klas = klas;
        this.grade = grade;
    }

    public Subclass(Long id, String klas, int grade, List<Subject> subjects) {
        this.id = id;
        this.klas = klas;
        this.grade = grade;
        this.subjects = subjects;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Subclass{" +
                "id=" + id +
                ", klas='" + klas + '\'' +
                ", grade=" + grade  +
                '}';
    }
}
