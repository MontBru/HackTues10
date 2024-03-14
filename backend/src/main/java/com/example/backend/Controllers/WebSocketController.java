package com.example.backend.Controllers;

import com.example.backend.Classes.MyUser;
import com.example.backend.Classes.Subclass;
import com.example.backend.Classes.Subject;
import com.example.backend.Repositories.UserRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebSocketController {

    private final UserRepository userRepository;

    public WebSocketController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @MessageMapping("/evaluateStudents?subclass={subclass}&subject={subject}")
    @SendTo("/topic/lowEvaluationStudents")
    public List<Long> evaluateStudents(@RequestParam Subclass subclass,@RequestParam Subject subject) {
        List<Long> lowEvaluationStudentIds = new ArrayList<>();
        for (MyUser user : subclass.getUsers())
        {
            int avg = userRepository.getEvaluation(user.getId(),subject.getStartedAt());
            if (avg < 4) {
                lowEvaluationStudentIds.add(user.getId());
            }
        }
        return lowEvaluationStudentIds;
    }
}
