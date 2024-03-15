package com.example.backend.Repositories;

import com.example.backend.Classes.Stats;
import com.example.backend.Classes.Subclass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubClassRepository extends JpaRepository<Subclass, Long> {
}
