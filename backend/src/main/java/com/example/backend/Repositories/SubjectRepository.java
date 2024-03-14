package com.example.backend.Repositories;

import com.example.backend.Classes.Stats;
import com.example.backend.DTO.HREntryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface SubjectRepository  extends JpaRepository<Stats, Long> {

    /*@Query("SELECT AVERAGE(hr.evaluation), hr.value, hr.created_at FROM Subject s LEFT JOIN subclass_table sc ON sc.id = s.class_subjects_id" +
            "LEFT JOIN user_table u ON u.id = sc.user_class_id" +
            "RIGHT JOIN hr_entry hr ON hr.user_id = u.id" +
            "WHERE s.name = name ")
    List<HREntryDTO> getAllEntries(String name);*/

    @Query("SELECT new map(s.name as subject, AVG(h.evaluation) as evaluation_average, " +
            "COLLECT_LIST(h.value) as pulse_values) " +
            "FROM Subject s " +
            "JOIN s.class_subjects c " +
            "JOIN c.user_class u " +
            "JOIN u.hrEntries h " +
            "WHERE u.id = :id AND FUNCTION('DAY', h.createdAt) = FUNCTION('DAY', :date)")
    List<Map<String, Object>> getSubjectHREntries(Long id, LocalDate date);



}
