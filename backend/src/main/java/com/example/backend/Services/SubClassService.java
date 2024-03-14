package com.example.backend.Services;


import com.example.backend.Classes.HrEntry;
import com.example.backend.Classes.MyUser;
import com.example.backend.Classes.Subclass;
import com.example.backend.Repositories.SubClassRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubClassService {

    @Autowired
    SubClassRepository subClassRepository;

    @Autowired
    UserRepository userRepository;

    public double subClassAVG(Long subClassID){
        Subclass SubClass = subClassRepository.findById(subClassID).orElse(null);

        double totalEvaluation = 0;
        int totalCount = 0;
        if(SubClass != null)
        {


            List<MyUser> users = userRepository.findAll();

            for(MyUser user : users) {
                if(user.getClasses().contains(SubClass)) {
                    for(HrEntry hr : user.getHrEntries()) {
                        totalEvaluation += hr.getEvaluation();
                        totalCount++;
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
