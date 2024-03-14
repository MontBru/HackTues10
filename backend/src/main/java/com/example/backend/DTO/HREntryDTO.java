package com.example.backend.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public class HREntryDTO {

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)

    private Date createdAt;


    private int value;


    private int evaluation;

    public HREntryDTO(Date createdAt, int value, int evaluation) {
        this.createdAt = createdAt;
        this.value = value;
        this.evaluation = evaluation;
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
        return "HREntryDTO{" +
                "createdAt=" + createdAt +
                ", value=" + value +
                ", evaluation=" + evaluation +
                '}';
    }
}
