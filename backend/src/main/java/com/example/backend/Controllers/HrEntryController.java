package com.example.backend.Controllers;


import com.example.backend.DTO.HREntryDTO;
import com.example.backend.Services.HrEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hrEntry")
public class HrEntryController
{
    @Autowired
    HrEntryService hrEntryService;

    @PostMapping(path = "/createEntry")
    public void createEntry(@RequestBody List<HREntryDTO> data) throws Exception {
        hrEntryService.createHrEntryWithList(data);
    }
}
