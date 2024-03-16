package com.example.backend.Repositories;

import com.example.backend.Classes.Stats;
import com.example.backend.DTO.HREntryDTO;
import com.example.backend.DTO.SubjectAttentionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface SubjectRepository  extends JpaRepository<Stats, Long> {

    /*@Query("SELECT new com.example.backend.DTO.SubjectAttentionDTO(s.name, AVG(h.evaluation)) " +
            "FROM Subject s " +
            "JOIN s.class_subjects c " +
            "JOIN c.user_class u " +
            "JOIN u.hrEntries h " +
            "WHERE u.id = :id AND  h.createdAt > :d1 AND h.createdAt < :d2")
    List<SubjectAttentionDTO> getSubjectHREntries(@Param("id") Long id, @Param("d1") LocalDateTime d1, @Param("d2") LocalDateTime d2);
*/



}
