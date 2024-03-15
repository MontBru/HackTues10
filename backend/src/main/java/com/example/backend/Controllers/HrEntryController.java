package com.example.backend.Controllers;


import com.example.backend.DTO.HREntryDTO;
import com.example.backend.Services.HrEntryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hrEntry")
public class HrEntryController
{
    @Autowired
    HrEntryService hrEntryService;

    @PostMapping(path = "/createEntry")
    public void createEntry(@RequestBody List<HREntryDTO> data) throws Exception {
        System.out.println(data);
        hrEntryService.createHrEntryWithList(data);
    }

    @PutMapping("/updateEval/{eval}")
    public void updateEval(@RequestBody List<Long> entries, @PathVariable int eval)
    {
        hrEntryService.updateHREntries(entries,eval);
    }
}
