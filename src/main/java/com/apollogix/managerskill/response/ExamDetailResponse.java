package com.apollogix.managerskill.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ExamDetailResponse extends ExamResponse {

    private List<QuestionResponse> lstQuestions;
}
