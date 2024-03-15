package com.example.backend.Controllers;


import com.example.backend.Classes.Subclass;
import com.example.backend.DTO.SubclassDTO;
import com.example.backend.DTO.UserAttentionDTO;
import com.example.backend.Services.SubClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subclass")
public class SubclassController {

    @Autowired
    SubClassService subClassService;


    @GetMapping(path = "/subClassAVG/{klas}/{grade}/{zone}")
    public double subClassAVG(@PathVariable String klas, @PathVariable String grade, @PathVariable String zone)
    {

        return subClassService.subClassAVG(klas, grade, zone);
    }

    @GetMapping("/studentsWithLostAttention/{klas}/{grade}")
    public List<UserAttentionDTO> studentsTired(@PathVariable String klas, @PathVariable String grade)
    {
        return subClassService.getTiredStudents(klas, grade);
    }

    @GetMapping(path = "/subClassStudentsAVG/{subClassID}" )
    public Map<Integer, Double> subClassStudentsAVG(@PathVariable int subClassID)
    {
        return subClassService.subClassStudentsAVG(subClassID);
    }
}
