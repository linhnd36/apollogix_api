package com.apollogix.managerskill.controller;

import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.request.CreateExamRequest;
import com.apollogix.managerskill.request.UserUpdateRequest;
import com.apollogix.managerskill.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody CreateExamRequest request) {
        return new ResponseEntity<>(examService.create(request), HttpStatus.CREATED);
    }

    /**
     * Retrieves an Exam by its ID.
     *
     * @param id the ID of the Exam to retrieve
     * @return a ResponseEntity object containing the Exam information
     * @throws BusinessException if an error occurs while retrieving the Exam
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable Integer id) throws BusinessException {
        return new ResponseEntity<>(examService.getExamById(id), HttpStatus.CREATED);
    }

    /**
     * API Delete Exam by Id
     * @param id Exam
     * @return ResponseEntity
     * @throws BusinessException if id exam doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BusinessException {
        return examService.delete(id);
    }
}
