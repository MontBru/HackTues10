package com.example.backend.Controllers;


import com.example.backend.DTO.HREntryDTO;
import com.example.backend.Services.SubjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired SubjectServices subjectService;

    @GetMapping("/getAllHREtries/{id}/{date}")
    public List<Map<String, Object>>  getAllHREtries(@RequestParam Long id, @RequestParam LocalDate date){
        return subjectService.getAllEntries(id,date);
    }


}
