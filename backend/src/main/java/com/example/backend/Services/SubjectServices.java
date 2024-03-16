package com.example.backend.Services;


import com.example.backend.DTO.SubjectAttentionDTO;
import com.example.backend.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubjectServices {
    @Autowired
    SubjectRepository subjectRepository;
    public List<SubjectAttentionDTO>  getSubjectEntries(Long id, LocalDate date) {
        LocalDateTime d1 = date.atStartOfDay();
        LocalDateTime d2 = date.atTime(23,59,59);
        return subjectRepository.getSubjectHREntries(id, d1,d2);
    }

}
