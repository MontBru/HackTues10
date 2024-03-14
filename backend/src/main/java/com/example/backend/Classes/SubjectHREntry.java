package com.example.backend.Classes;

import java.util.List;

public class SubjectHREntry
{
    private int evaluation_average;

    private List<Integer> pulse_value;

    public SubjectHREntry(int evaluation_average, List<Integer> pulse_value) {
        this.evaluation_average = evaluation_average;
        this.pulse_value = pulse_value;
    }

    public int getEvaluation_average() {
        return evaluation_average;
    }

    public void setEvaluation_average(int evaluation_average) {
        this.evaluation_average = evaluation_average;
    }

    public List<Integer> getPulse_value() {
        return pulse_value;
    }

    public void setPulse_value(List<Integer> pulse_value) {
        this.pulse_value = pulse_value;
    }

    @Override
    public String toString() {
        return "SubjectHREntry{" +
                "evaluation_average=" + evaluation_average +
                ", pulse_value=" + pulse_value +
                '}';
    }
}
