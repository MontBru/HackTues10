package com.example.backend.Repositories;

import com.example.backend.Classes.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long> {
    Optional<Object> findByEmail(String username);

}
