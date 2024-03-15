package com.example.backend.DTO;

import jakarta.persistence.Column;

public class SubclassDTO {
    private String klas;
    private String grade;

    public SubclassDTO(String klas, String grade) {
        this.klas = klas;
        this.grade = grade;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}