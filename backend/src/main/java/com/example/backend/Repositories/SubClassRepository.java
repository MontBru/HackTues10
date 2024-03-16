package com.example.backend.Repositories;

import com.example.backend.Classes.Stats;
import com.example.backend.Classes.Subclass;
import com.example.backend.DTO.UserAttentionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SubClassRepository extends JpaRepository<Subclass, Long>
{

    @Query("SELECT new com.example.backend.DTO.UserAttentionDTO(u.name , hr.evaluation) FROM MyUser u " +
            "JOIN u.hrEntries hr " +
            "JOIN u.classes c " +
            "WHERE c.klas = :klas AND c.grade = :grade AND hr.createdAt > :date AND hr.evaluation < 5")
    List<UserAttentionDTO> getTiredStudents(String klas, String grade, LocalDateTime date);

    Optional<Subclass> findByKlasAndGrade(String klas, String grade);


}
