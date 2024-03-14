package com.example.backend.Services;


import com.example.backend.DTO.HREntryDTO;
import com.example.backend.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServices {
    @Autowired
    SubjectRepository subjectRepository;
    public List<HREntryDTO> getAllEntries(String name) {
        return null;
    }
}
