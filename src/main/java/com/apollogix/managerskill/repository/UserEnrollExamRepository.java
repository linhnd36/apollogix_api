package com.apollogix.managerskill.repository;

import com.apollogix.managerskill.entity.UserEnrollExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserEnrollExamRepository extends JpaRepository<UserEnrollExam, Integer> {

    @Query("SELECT u FROM UserEnrollExam u WHERE u.user.id = :id")
    List<UserEnrollExam> findByUserId(Integer id);
}