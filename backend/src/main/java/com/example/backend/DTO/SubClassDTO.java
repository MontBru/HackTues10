package com.example.backend.DTO;

public class SubClassDTO {

    private String klas;
    private String grade;

    public SubClassDTO(String klas, String grade) {
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
