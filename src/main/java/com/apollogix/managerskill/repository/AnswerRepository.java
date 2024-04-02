package com.apollogix.managerskill.repository;

import com.apollogix.managerskill.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query("SELECT a FROM Answer a WHERE a.id IN :ids")
    List<Answer> getAnswerByListId(List<Integer> ids);

}