package com.example.backend.Repositories;

import com.example.backend.Classes.Stats;
import com.example.backend.Classes.Subclass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubClassRepository extends JpaRepository<Subclass, Long> {
    Optional<Subclass> findByKlasAndGrade(String klas, String grade);
}
