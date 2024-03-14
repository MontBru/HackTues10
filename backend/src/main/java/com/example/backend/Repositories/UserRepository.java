package com.example.backend.Repositories;

import com.example.backend.Classes.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long>
{
    @Query("SELECT AVERAGE(hr.evaluation) FROM HrEntry hr JOIN hr.user u WHERE u.id = :id AND hr.createdAt > :date ")
    int getEvaluation(Long id, LocalDate date);
    Optional<Object> findByEmail(String username);

}
