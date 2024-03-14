package com.example.backend.Classes;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "day")
    private int day;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "started_at", nullable = false, updatable = false)
    private Date startedAt;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ended_at", nullable = false, updatable = false)
    private Date endedAt;

    @ManyToOne
    @JsonManagedReference
    private Subclass class_subjects;


    public Subject(Long id, String name, int day, Date startedAt, Date endedAt) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public Subject() {}

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }
}
