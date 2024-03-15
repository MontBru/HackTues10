package com.example.backend.Services;


import com.example.backend.Classes.HrEntry;
import com.example.backend.Classes.MyUser;
import com.example.backend.Classes.Subclass;
import com.example.backend.DTO.SubclassDTO;
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

    public double subClassAVG(String klas, String grade, String zone){
        Subclass SubClass = subClassRepository.findByKlasAndGrade(klas, grade).orElse(null);
        LocalDateTime dateTimeLimit = LocalDateTime.now();
        /*Calendar calendar = Calendar.getInstance();
        if (zone.equals("month")) {
             calendar.add(Calendar.MONTH, -1);
        } else if (zone.equals("week")) {
            calendar.add(Calendar.WEEK_OF_MONTH, -1);
        } else {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }*/
        if(zone.equals("mounth"))
        {
            dateTimeLimit = dateTimeLimit.minus(1,ChronoUnit.MONTHS);
        }
        else if(zone.equals("week"))
        {
            dateTimeLimit = dateTimeLimit.minus(1,ChronoUnit.WEEKS);
        }
        else
        {
            dateTimeLimit = dateTimeLimit.minus(1,ChronoUnit.DAYS);
        }

        double totalEvaluation = 0;
        int totalCount = 0;
        if(SubClass != null)
        {


            List<MyUser> users = userRepository.findAll();

            for(MyUser user : users) {
                if(user.getClasses().contains(SubClass)) {
                    for(HrEntry hr : user.getHrEntries()) {
                        if(hr.getCreatedAt().isAfter(dateTimeLimit))
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
