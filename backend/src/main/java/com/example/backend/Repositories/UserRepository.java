package com.example.backend.Repositories;

import com.example.backend.Classes.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long>
{
    @Query("SELECT AVG(hr.evaluation) FROM HrEntry hr JOIN hr.user u WHERE u.id = :id AND hr.createdAt > :date")
    int getEvaluation(Long id, Date date);

    default int getEvaluationAVGDate(Long id, LocalDate startedAt) {
        Date date = Date.from(startedAt.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return getEvaluation(id, date);
    }
    Optional<Object> findByEmail(String username);

    @Query("SELECT u FROM MyUser u WHERE u.device_id = :id")
    MyUser getByDeviceId(String id);
}
