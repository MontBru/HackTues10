package com.example.backend.Classes;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
public class HrEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name= "value")
    private int value;

    @Column(name = "evaluation")
    private int evaluation;

    @ManyToOne
    @JsonManagedReference
    private User user;

    public HrEntry() {}

    public HrEntry(Long id, Date createdAt, int value, int evaluation) {
        this.id = id;
        this.createdAt = createdAt;
        this.value = value;
        this.evaluation = evaluation;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "HrEntry{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", value=" + value +
                ", evaluation=" + evaluation +
                '}';
    }
}
