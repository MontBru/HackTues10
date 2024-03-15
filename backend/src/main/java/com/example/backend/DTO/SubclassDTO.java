package com.example.backend.DTO;

import jakarta.persistence.Column;

public class SubclassDTO
{
    private String klas;
    private int grade;

    public SubclassDTO(String klas, int grade) {
        this.klas = klas;
        this.grade = grade;
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
}
