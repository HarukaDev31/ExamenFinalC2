package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    //Get user data employee data, bank accounts data, and salary data
    @Query("SELECT u FROM User u JOIN FETCH u.employee e JOIN FETCH e.bankAccounts b JOIN FETCH e.salaries WHERE u.id = ?1")
    List<User> findProfileByEmail(String email);
}
