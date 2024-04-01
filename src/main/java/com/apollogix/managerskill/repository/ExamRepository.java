package com.apollogix.managerskill.repository;

import com.apollogix.managerskill.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    Page<Exam> findByNameIgnoreCaseContainingAndIsDelete(String name, Boolean isDelete, Pageable pageable);
}