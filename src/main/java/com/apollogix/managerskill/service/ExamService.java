package com.apollogix.managerskill.service;

import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.request.CreateExamRequest;
import com.apollogix.managerskill.response.ExamResponse;
import org.springframework.http.ResponseEntity;

public interface ExamService {

    /**
     * Create Exam, Question and Answer
     *
     * @param request data create exam
     * @return id exam after create successful
     */
    Integer create(CreateExamRequest request);

    /**
     * Get Exam By id
     *
     * @param id exam
     * @return Exam info
     */
    ExamResponse getExamById(Integer id) throws BusinessException;

    /**
     * Delete Exam by Id
     *
     * @param id exam
     * @return ResponseEntity
     * @throws BusinessException if id exam not exit in database
     */
    ResponseEntity<?> delete(Integer id) throws BusinessException;
}
