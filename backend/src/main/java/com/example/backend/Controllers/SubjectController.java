package com.example.backend.Controllers;


import com.example.backend.Classes.MyUser;
import com.example.backend.DTO.HREntryDTO;
import com.example.backend.DTO.SubjectAttentionDTO;
import com.example.backend.Services.SubjectServices;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired SubjectServices subjectService;

   /* @GetMapping("/getUserHREntries/{id}/{date}")
    public List<SubjectAttentionDTO>  getHREtries(@PathVariable Long id, @PathVariable Long date){
        LocalDateTime instant = LocalDateTime.ofEpochSecond(date/1000,0, ZoneOffset.UTC);
        LocalDate date1 = instant.toLocalDate();
        System.out.println(id);
        System.out.println(date1);
        return subjectService.getSubjectEntries(id,date1);
    }*/


}
