package com.example.backend.Repository;

import com.example.backend.Classes.Stats;
import com.example.backend.DTO.HREntryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository  extends JpaRepository<Stats, Long> {

    @Query("SELECT hr.evaluation, hr.value, hr.created_at FROM Subject s LEFT JOIN subclass_table sc ON sc.id = s.class_subjects_id" +
            "LEFT JOIN user_table u ON u.id = sc.user_class_id" +
            "RIGHT JOIN hr_entry hr ON hr.user_id = u.id" +
            "WHERE s.name = name ")
    List<HREntryDTO> getAllEntries(String name);
}
