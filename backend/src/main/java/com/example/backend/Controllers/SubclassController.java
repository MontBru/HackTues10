package com.example.backend.Controllers;


import com.example.backend.Services.SubClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
