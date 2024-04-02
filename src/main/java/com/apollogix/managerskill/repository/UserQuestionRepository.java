package com.apollogix.managerskill.repository;

import com.apollogix.managerskill.entity.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, Integer> {
}