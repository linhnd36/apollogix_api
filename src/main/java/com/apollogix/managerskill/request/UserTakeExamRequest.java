package com.apollogix.managerskill.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserTakeExamRequest {

    // Exam id current user takes
    private Integer examId;

    // This Key is id question, and Value is list id answer user select
    private Map<Integer, List<Integer>> questionWithAnswer;
}
