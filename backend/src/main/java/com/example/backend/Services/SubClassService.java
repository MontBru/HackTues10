package com.example.backend.Services;


import com.example.backend.Classes.HrEntry;
import com.example.backend.Classes.MyUser;
import com.example.backend.Classes.Subclass;
import com.example.backend.DTO.StudentsInClassDTO;
import com.example.backend.DTO.SubAttDTO;
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

    public int subClassAVG(String klas, String grade, String zone){
        Subclass SubClass = subClassRepository.findByKlasAndGrade(klas, grade).orElse(null);
        LocalDateTime dateTimeLimit = LocalDateTime.now();
        int flag = 0;
        if(zone.equals("mounth"))
        {
            dateTimeLimit = dateTimeLimit.minus(1,ChronoUnit.MONTHS);
            flag = 2;
        }
        else if(zone.equals("week"))
        {
            dateTimeLimit = dateTimeLimit.minus(1,ChronoUnit.WEEKS);
            flag = 1;
        }
        else
        {
            dateTimeLimit = dateTimeLimit.minus(1,ChronoUnit.DAYS);
        }

        int totalEvaluation = 0;
        int totalCount = 0;

        List<Long> userIDs = subClassRepository.getClassAverage(klas,grade);
        for(Long id : userIDs)
        {
            totalEvaluation = totalEvaluation + userRepository.getEvaluation(id, dateTimeLimit);
            totalCount++;
        }

        return totalEvaluation / totalCount;
    }

    public Map<Integer, Double> subClassStudentsAVG(int subClassID) {

        return null;
    }

    public List<UserAttentionDTO> getTiredStudents(String klas, String grade)
    {
        Subclass subclass = subClassRepository.findByKlasAndGrade(klas, grade).orElse(null);
        if(subclass!= null)
        {
            LocalDateTime date = LocalDateTime.now().minusSeconds(5);
            return subClassRepository.getTiredStudents(subclass.getKlas(),subclass.getGrade(),date);
        }
        else
        {
            return null;
        }

    }


    public List<StudentsInClassDTO> getStudentsFromClass(String klas, String grade)
    {
        return subClassRepository.getStudentsByClass(klas, grade);
    }
}
