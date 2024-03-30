package com.apollogix.managerskill.request;


import lombok.Data;

@Data
public class CreateAnswerRequest {

    private String name;

    private Boolean isCorrect = false;

}
