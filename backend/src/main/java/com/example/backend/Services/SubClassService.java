package com.example.backend.Services;


import com.example.backend.Classes.HrEntry;
import com.example.backend.Classes.MyUser;
import com.example.backend.Classes.Subclass;
import com.example.backend.Repositories.SubClassRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SubClassService {

    @Autowired
    SubClassRepository subClassRepository;

    @Autowired
    UserRepository userRepository;

    public double subClassAVG(Long subClassID, String zone){
        Subclass SubClass = subClassRepository.findById(subClassID).orElse(null);
        Date dateTimeLimit = new Date();
        Calendar calendar = Calendar.getInstance();
        if (zone.equals("month")) {
             calendar.add(Calendar.MONTH, -1);
        } else if (zone.equals("week")) {
            calendar.add(Calendar.WEEK_OF_MONTH, -1);
        } else {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }

        dateTimeLimit = calendar.getTime();

        double totalEvaluation = 0;
        int totalCount = 0;
        if(SubClass != null)
        {
            List<MyUser> users = userRepository.findAll();

            for(MyUser user : users) {
                if(user.getClasses().contains(SubClass)) {
                    for(HrEntry hr : user.getHrEntries()) {
                        if(hr.getCreatedAt().after(dateTimeLimit))
                        {
                            totalEvaluation += hr.getEvaluation();
                            totalCount++;
                        }

                    }
                }
            }


        }
        if(totalCount > 0)
        {
            totalEvaluation/=totalCount;

        }
        return totalEvaluation;
    }
}
