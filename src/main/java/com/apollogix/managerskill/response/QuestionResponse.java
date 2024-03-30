package com.apollogix.managerskill.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Integer id;

    private String name;

    private List<AnswerResponse> lstAnswers;
}
