package com.example.backend.Services;


import com.example.backend.DTO.HREntryDTO;
import com.example.backend.DTO.SubjectAttentionDTO;
import com.example.backend.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class SubjectServices {
    @Autowired
    SubjectRepository subjectRepository;
    public List<SubjectAttentionDTO>  getSubjectEntries(Long id, LocalDate date) {
        return subjectRepository.getSubjectHREntries(id, date);
    }

}
