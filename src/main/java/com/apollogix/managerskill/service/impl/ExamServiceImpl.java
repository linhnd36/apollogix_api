package com.apollogix.managerskill.service.impl;

import com.apollogix.managerskill.entity.Answer;
import com.apollogix.managerskill.entity.Exam;
import com.apollogix.managerskill.entity.Question;
import com.apollogix.managerskill.entity.UserEnrollExam;
import com.apollogix.managerskill.exception.BusinessException;
import com.apollogix.managerskill.mapper.AnswerMapper;
import com.apollogix.managerskill.mapper.ExamMapper;
import com.apollogix.managerskill.mapper.QuestionMapper;
import com.apollogix.managerskill.repository.AnswerRepository;
import com.apollogix.managerskill.repository.ExamRepository;
import com.apollogix.managerskill.repository.QuestionRepository;
import com.apollogix.managerskill.repository.UserEnrollExamRepository;
import com.apollogix.managerskill.request.*;
import com.apollogix.managerskill.response.ExamDetailResponse;
import com.apollogix.managerskill.response.PaginationSortResponse;
import com.apollogix.managerskill.response.QuestionResponse;
import com.apollogix.managerskill.service.ExamService;
import com.apollogix.managerskill.service.MessageService;
import com.apollogix.managerskill.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.apollogix.managerskill.constants.Constants.ASC;
import static com.apollogix.managerskill.constants.Constants.DESC;
import static com.apollogix.managerskill.constants.MessageLabel.*;


@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserEnrollExamRepository userEnrollExamRepository;

    @Override
    public Integer create(CreateExamRequest request) {
        Exam exam = Exam.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createBy(userService.getCurrentUser())
                .isDelete(false)
                .build();

        List<CreateQuestionRequest> questionRequests = request.getLstQuestion();
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        for (CreateQuestionRequest q : questionRequests) {
            // Create Question for Exam
            Question question = Question.builder()
                    .name(q.getName())
                    .isDelete(false)
                    .exam(exam).build();
            questions.add(question);
            // Create Answer for one Question
            List<CreateAnswerRequest> lstAnswer = q.getLstAnswer();
            for (CreateAnswerRequest a : lstAnswer) {
                answers.add(Answer.builder()
                        .name(a.getName())
                        .question(question)
                        .isDelete(false)
                        .isCorrect(a.getIsCorrect()).build());
            }
        }
        examRepository.save(exam);
        questionRepository.saveAll(questions);
        answerRepository.saveAll(answers);
        return exam.getId();
    }

    @Override
    public ExamDetailResponse getExamById(Integer id) throws BusinessException {
        Exam exam = checkExistExamById(id);
        if (exam.getIsDelete()) {
            throw new BusinessException(messageService.getMessages(MSG_VALIDATE_EXAM_ALREADY_DELETE));
        }
        ExamDetailResponse examDetailResponse = ExamMapper.INSTANCE.toDetailResponse(exam);
        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Question q : exam.getQuestions()) {
            QuestionResponse questionResponse = QuestionMapper.INSTANCE.toResponse(q);
            questionResponses.add(questionResponse);
            questionResponse.setLstAnswers(AnswerMapper.INSTANCE.listToResponse(q.getAnswers()));
        }
        examDetailResponse.setLstQuestions(questionResponses);
        return examDetailResponse;
    }

    @Override
    public ResponseEntity<?> delete(Integer id) throws BusinessException {
        Exam exam = checkExistExamById(id);
        exam.setIsDelete(false);
        examRepository.save(exam);
        return ResponseEntity.ok().build();
    }

    @Override
    public PaginationSortResponse searchByName(SearchExamRequest request) throws BusinessException {
        Pageable paging = null;
        if (ObjectUtils.isEmpty(request.getSort())){
            paging = PageRequest.of(request.getPage(), request.getSize());
        } else {
            Sort sort;
            for (Map.Entry<String, String> entry: request.getSort().entrySet()){
                if (DESC.equalsIgnoreCase(entry.getValue())){
                    sort = Sort.by(entry.getKey()).descending();
                } else if (ASC.equalsIgnoreCase(entry.getValue())){
                    sort = Sort.by(entry.getKey()).ascending();
                } else {
                    throw new BusinessException(messageService.getMessages(MSG_VALIDATE_SORT_DIRECTION_INVALID));
                }
                paging = PageRequest.of(request.getPage(), request.getSize(), sort);
            }
        }
        Page<Exam> examPage = examRepository.findByNameIgnoreCaseContainingAndIsDelete(request.getName(), false, paging);
        return PaginationSortResponse.builder()
                .totalElements(examPage.getTotalElements())
                .totalPages(examPage.getTotalPages())
                .currentPage(request.getPage())
                .size(request.getSize())
                .objects(ExamMapper.INSTANCE.listToResponse(examPage.getContent()))
                .build();
    }

    @Override
    public ResponseEntity<?> takeExam(UserTakeExamRequest request) throws BusinessException {
        Exam exam = checkExistExamById(request.getExamId());
        UserEnrollExam userEnrollExam = UserEnrollExam.builder()
                .user(userService.getCurrentUser())
                .exam(exam).build();
        userEnrollExamRepository.save(userEnrollExam);
        for (Map.Entry<Integer, List<Integer>> entry : request.getQuestionWithAnswer().entrySet()){

        }
        return null;
    }

    /**
     * Check Exist Exam
     *
     * @param id Exam
     * @return Exam entity
     * @throws BusinessException if have validate error
     */
    private Exam checkExistExamById(Integer id) throws BusinessException {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isEmpty()) {
            throw new BusinessException(messageService.getMessages(MSG_VALIDATE_USER_DO_NOT_EXIST));
        } else {
            return exam.get();
        }
    }

}
