package com.apollogix.managerskill.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponse {

    private Integer id;

    private String name;

    private String description;

    private List<QuestionResponse> lstQuestions;
}
