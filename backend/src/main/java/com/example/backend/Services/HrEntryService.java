package com.example.backend.Services;

import com.example.backend.Classes.HrEntry;
import com.example.backend.Classes.MyUser;
import com.example.backend.DTO.HREntryDTO;
import com.example.backend.Repositories.HrEntryRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HrEntryService {

    @Autowired
    HrEntryRepository hrEntryRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AiService aiService;

    public double TakeMinValue(int[] values){
        double mediana = 0;
        double first_cfartil =  0;
        if(values.length == 0)
            return 60;

        if(values.length == 1)
            return values[0];


        if(values.length % 2 == 0)
        {
            first_cfartil = values[values.length/2];
        }
        else
        {
            first_cfartil = values[(values.length + 1) / 2 - 1];
        }
        if(first_cfartil % 2 == 0)
        {
            mediana = (double) (values[values.length / 2] + values[(values.length / 2)]) / 2;
        }
        else
        {
            mediana = values[(values.length+1) / 2];
        }
        return mediana;
    }

    public double TakeMaxValue(int[] values){
        int[] values2 = new int[1];
        int index = 0;

        if(values.length == 0)
            return 70;

        if(values.length == 1)
            return values[0];

        for(int i = values.length/2; i<values.length; i++)
        {
            values2[index] = values[i];
        }
        values = values2;
        double mediana = 0;
        double first_cfartil =  0;
        if(values.length % 2 == 0)
        {
            first_cfartil = values[values.length/2];
        }
        else
        {
            first_cfartil = values[(values.length + 1) / 2  - 1];
        }
        if(first_cfartil % 2 == 0)
        {
            mediana = (double) (values[values.length / 2] + values[(values.length / 2)]) / 2;
        }
        else
        {
            mediana = values[(values.length+1) / 2 - 1];
        }
        return mediana;
    }

    public void createHrEntryWithList(List<HREntryDTO> data) throws Exception {
        for (HREntryDTO datum : data) {
            MyUser user = userRepository.getByDeviceId(datum.getId());
            createHrEntry(datum.getValue(), user);
        }
    }

    public void createHrEntry(int value, MyUser user) throws Exception {
        int[] values = hrEntryRepository.takeLimitedEntries(user.getId());

        double minValue = TakeMinValue(values);
        double maxValue = TakeMaxValue(values);

        int evaluate = aiService.AiRequest(minValue, maxValue, value);
        hrEntryRepository.save(new HrEntry(LocalDateTime.now(), value, evaluate, user));
    }


    public void updateHREntries(List<Long> entries, int eval)
    {
        for(int i = 0; i < entries.size(); i++)
        {
            hrEntryRepository.getReferenceById(entries.get(i)).setEvaluation(eval);
        }
    }

    public void createHrEntryTimestamp(List<HREntryDTO> data, LocalDateTime timestamp) throws Exception {
        for (HREntryDTO datum : data) {
            MyUser user = userRepository.getByDeviceId(datum.getId());
            createHrEntryWithTimestamp(datum.getValue(), user, timestamp);
        }
    }

    private void createHrEntryWithTimestamp(int value, MyUser user, LocalDateTime timestamp) throws Exception {
        int[] values = hrEntryRepository.takeLimitedEntries(user.getId());

        double minValue = TakeMinValue(values);
        double maxValue = TakeMaxValue(values);

        int evaluate = aiService.AiRequest(minValue, maxValue, value);
        hrEntryRepository.save(new HrEntry(timestamp, value, evaluate, user));
    }
}
