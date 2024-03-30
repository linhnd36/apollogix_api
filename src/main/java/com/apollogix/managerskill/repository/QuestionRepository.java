package com.apollogix.managerskill.repository;

import com.apollogix.managerskill.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}