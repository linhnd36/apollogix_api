package com.apollogix.managerskill.request;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

import java.util.List;

@Data
public class CreateExamRequest {

    private String name;

    private String description;

    private List<CreateQuestionRequest> lstQuestion;
}
