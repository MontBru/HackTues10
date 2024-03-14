package com.example.backend.DTO;

import com.example.backend.Classes.Subclass;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public class SubjectDTO {

    private String name;


    private int day;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedAt;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date endedAt;


    public SubjectDTO(String name, int day, Date startedAt, Date endedAt) {
        this.name = name;
        this.day = day;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
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

    @Override
    public String toString() {
        return "SubjectDTO{" +
                "name='" + name + '\'' +
                ", day=" + day +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                '}';
    }
}
