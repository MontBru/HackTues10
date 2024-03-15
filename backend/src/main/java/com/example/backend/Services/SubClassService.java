package com.example.backend.Services;


import com.example.backend.Classes.HrEntry;
import com.example.backend.Classes.MyUser;
import com.example.backend.Classes.Subclass;
import com.example.backend.DTO.UserAttentionDTO;
import com.example.backend.Repositories.SubClassRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SubClassService {

    @Autowired
    SubClassRepository subClassRepository;

    @Autowired
    UserRepository userRepository;

    public double subClassAVG(int subClassID, String zone){
        System.out.println("I am in here");
        Subclass SubClass = subClassRepository.findById((long) subClassID).orElse(null);
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

    public Map<Integer, Double> subClassStudentsAVG(int subClassID) {

        return null;
    }

    public List<UserAttentionDTO> getTiredStudents(Subclass subclass)
    {
        LocalDate date = LocalDate.now().minus(1, ChronoUnit.MINUTES);
        return subClassRepository.getTiredStudents(subclass.getKlas(),subclass.getGrade(),date);
    }
}
