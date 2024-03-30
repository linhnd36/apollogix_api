package com.apollogix.managerskill.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionRequest {

    private String name;

    private List<CreateAnswerRequest> lstAnswer;

}
