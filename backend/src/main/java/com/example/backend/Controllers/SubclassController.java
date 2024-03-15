package com.example.backend.Controllers;


import com.example.backend.Classes.Subclass;
import com.example.backend.DTO.SubclassDTO;
import com.example.backend.DTO.UserAttentionDTO;
import com.example.backend.DTO.UserDTO;
import com.example.backend.Services.SubClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subclass")
public class SubclassController {

    @Autowired
    SubClassService subClassService;


    @GetMapping(path = "/subClassAVG")
    public double subClassAVG(@RequestBody Long subClassID,@RequestBody String zone)
    {
        return subClassService.subClassAVG(subClassID, zone);
    }

    @GetMapping("/studentsWithLostAttention")
    public List<UserAttentionDTO> studentsTired(@RequestParam Subclass subclass)
    {
        return subClassService.getTiredStudents(subclass);
    }
}
