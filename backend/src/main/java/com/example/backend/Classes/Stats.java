package com.example.backend.Classes;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avg_pulse_min")
    private float avg_pulse_min;

    @Column(name = "avg_pulse_max")
    private float avg_pulse_max;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, updatable = false)
    private Date date;

    @Column(name= "avg_mood")
    private float avg_mood;

    public Stats(Long id, float avg_pulse_min, float avg_pulse_max, Date date, float avg_mood) {
        this.id = id;
        this.avg_pulse_min = avg_pulse_min;
        this.avg_pulse_max = avg_pulse_max;
        this.date = date;
        this.avg_mood = avg_mood;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAvg_pulse_min() {
        return avg_pulse_min;
    }

    public void setAvg_pulse_min(float avg_pulse_min) {
        this.avg_pulse_min = avg_pulse_min;
    }

    public float getAvg_pulse_max() {
        return avg_pulse_max;
    }

    public void setAvg_pulse_max(float avg_pulse_max) {
        this.avg_pulse_max = avg_pulse_max;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAvg_mood() {
        return avg_mood;
    }

    public void setAvg_mood(float avg_mood) {
        this.avg_mood = avg_mood;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", avg_pulse_min=" + avg_pulse_min +
                ", avg_pulse_max=" + avg_pulse_max +
                ", date=" + date +
                ", avg_mood=" + avg_mood +
                '}';
    }
}
