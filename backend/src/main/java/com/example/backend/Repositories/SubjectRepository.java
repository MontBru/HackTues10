package com.example.backend.Repositories;

import com.example.backend.Classes.Stats;
import com.example.backend.DTO.HREntryDTO;
import com.example.backend.DTO.SubjectAttentionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface SubjectRepository  extends JpaRepository<Stats, Long> {

    @Query("SELECT new com.example.backend.DTO.SubjectAttentionDTO(s.name, AVG(h.evaluation), " +
            "COLLECT_LIST(h)) " +
            "FROM Subject s " +
            "JOIN s.class_subjects c " +
            "JOIN c.user_class u " +
            "JOIN u.hrEntries h " +
            "WHERE s.id = :id AND s.startedAt < :date AND s.endedAt > :date")
    List<SubjectAttentionDTO> getSubjectHREntries(@Param("id") Long id, @Param("date") LocalDate date);




}
