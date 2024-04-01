package com.apollogix.managerskill.service;

import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.request.CreateExamRequest;
import com.apollogix.managerskill.request.SearchExamRequest;
import com.apollogix.managerskill.request.UserTakeExamRequest;
import com.apollogix.managerskill.response.ExamDetailResponse;
import com.apollogix.managerskill.response.PaginationSortResponse;
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
    ExamDetailResponse getExamById(Integer id) throws BusinessException;

    /**
     * Delete Exam by Id
     *
     * @param id exam
     * @return ResponseEntity
     * @throws BusinessException if id exam not exit in database
     */
    ResponseEntity<?> delete(Integer id) throws BusinessException;

    /**
     * Search Exam by Name
     *
     * @param request page, size, sort condition
     * @return PaginationSortResponse with a list object
     */
    PaginationSortResponse searchByName(SearchExamRequest request) throws BusinessException;

    /**
     * User take Exam
     * @param request UserTakeExamRequest
     * @return scope user have
     */
    ResponseEntity<?> takeExam(UserTakeExamRequest request) throws BusinessException;
}
