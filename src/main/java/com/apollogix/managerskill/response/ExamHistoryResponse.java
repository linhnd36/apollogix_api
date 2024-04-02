package com.apollogix.managerskill.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamHistoryResponse {

    private Integer id;

    private ExamResponse exam;

    private UserResponse user;

    private String result;

    private String createDate;
}
