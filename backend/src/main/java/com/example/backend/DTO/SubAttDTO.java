package com.example.backend.DTO;

public class SubAttDTO
{
    private String name;
    private int evaluation;

    public SubAttDTO(String name, int evaluation) {
        this.name = name;
        this.evaluation = evaluation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
}
