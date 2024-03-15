package com.example.backend.Repositories;

import com.example.backend.Classes.HrEntry;
import com.example.backend.Classes.Subclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HrEntryRepository extends JpaRepository<HrEntry, Long> {
    @Query("SELECT h.value FROM HrEntry h WHERE h.user.id = ?1 ORDER BY h.value LIMIT 10000")
    public int[] takeLimitedEntries(long id);

}
