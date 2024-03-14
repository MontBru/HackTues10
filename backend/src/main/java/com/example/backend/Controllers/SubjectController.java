package com.example.backend.Controllers;


import com.example.backend.DTO.HREntryDTO;
import com.example.backend.Services.SubjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired SubjectServices subjectService;

    @GetMapping("/getAllHREtries")
    public List<HREntryDTO> getAllHREtries(@RequestAttribute String name){
        return subjectService.getAllEntries(name);
    }

}
