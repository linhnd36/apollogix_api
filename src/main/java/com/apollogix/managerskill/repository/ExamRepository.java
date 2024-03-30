package com.apollogix.managerskill.repository;

import com.apollogix.managerskill.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}