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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired SubjectServices subjectService;

    @GetMapping("/getUserHREntries/{date}")
    public List<SubjectAttentionDTO>  getHREtries(HttpServletRequest request, @PathVariable LocalDate date){
        MyUser u = (MyUser) request.getAttribute("me");
        return subjectService.getSubjectEntries(u.getId(),date);
    }


}
