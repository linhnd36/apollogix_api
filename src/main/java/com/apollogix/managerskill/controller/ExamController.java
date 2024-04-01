package com.apollogix.managerskill.controller;

import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.request.CreateExamRequest;
import com.apollogix.managerskill.request.SearchExamRequest;
import com.apollogix.managerskill.request.UserTakeExamRequest;
import com.apollogix.managerskill.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * API for Teacher create Exam
     *
     * @param request the CreateExamRequest in include Question and Answer
     * @return a ResponseEntity object with the result of the register operation
     */
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateExamRequest request) {
        return new ResponseEntity<>(examService.create(request), HttpStatus.CREATED);
    }

    /**
     * Search exam by Name
     *
     * @param request condition search and pagination, sort
     * @return ResponseEntity with PaginationSortResponse
     * @throws BusinessException if condition error
     */
    @GetMapping()
    public ResponseEntity<?> searchByName(@RequestBody SearchExamRequest request) throws BusinessException {
        return new ResponseEntity<>(examService.searchByName(request), HttpStatus.OK);
    }

    /**
     * Get an Exam by its ID.
     *
     * @param id the ID of the Exam to retrieve
     * @return a ResponseEntity object containing the Exam information
     * @throws BusinessException if an error occurs while retrieving the Exam
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable Integer id) throws BusinessException {
        return new ResponseEntity<>(examService.getExamById(id), HttpStatus.OK);
    }

    /**
     * API Delete Exam by ID.
     *
     * @param id Exam
     * @return ResponseEntity
     * @throws BusinessException if id exam doesn't exist
     */
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BusinessException {
        return examService.delete(id);
    }

    /**
     * API For Student take exam
     *
     * @return ResponseEntity
     * @throws BusinessException if id exam doesn't exist
     */
    @PostMapping("/take-exam")
    public ResponseEntity<?> takeExam(@RequestBody UserTakeExamRequest request) throws BusinessException {
        return examService.takeExam(request);
    }


}
