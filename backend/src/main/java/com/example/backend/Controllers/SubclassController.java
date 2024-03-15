package com.example.backend.Controllers;


import com.example.backend.Classes.Subclass;
import com.example.backend.DTO.UserAttentionDTO;
import com.example.backend.DTO.UserDTO;
import com.example.backend.Services.SubClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subclass")
public class SubclassController {

    @Autowired
    SubClassService subClassService;


    @GetMapping(path = "/subClassAVG")
    public double subClassAVG(@RequestParam Long subClassID)
    {
        return subClassService.subClassAVG(subClassID);
    }

    @GetMapping("/studentsWithLostAttention")
    public List<UserAttentionDTO> studentsTired(@RequestParam Subclass subclass)
    {
        return subClassService.getTiredStudents(subclass);
    }
}
