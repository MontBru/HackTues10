package com.example.backend.DTO;

import java.util.List;

public class SubjectAttentionDTO
{
    private String subjectName;
    private int average_attention;

    private List<HREntryDTO> entries;

    public SubjectAttentionDTO(String subjectName, int average_attention, List<HREntryDTO> entries) {
        this.subjectName = subjectName;
        this.average_attention = average_attention;
        this.entries = entries;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getAverage_attention() {
        return average_attention;
    }

    public void setAverage_attention(int average_attention) {
        this.average_attention = average_attention;
    }

    public List<HREntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<HREntryDTO> entries) {
        this.entries = entries;
    }
}
