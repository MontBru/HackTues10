package com.example.backend.Controllers;


import com.example.backend.Classes.Subclass;
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


    @GetMapping(path = "/subClassAVG")
    public double subClassAVG(@RequestBody int subClassID,@RequestBody String zone)
    {
        return subClassService.subClassAVG(subClassID, zone);
    }

    @GetMapping("/studentsWithLostAttention")
    public List<UserAttentionDTO> studentsTired(@RequestParam Subclass subclass)
    {
        return subClassService.getTiredStudents(subclass);
    }

    @GetMapping(path = "/subClassStudentsAVG/{subClassID}" )
    public Map<Integer, Double> subClassStudentsAVG(@PathVariable int subClassID)
    {
        return subClassService.subClassStudentsAVG(subClassID);
    }
}
