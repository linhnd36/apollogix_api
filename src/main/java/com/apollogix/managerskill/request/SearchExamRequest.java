package com.apollogix.managerskill.request;

import lombok.Data;

import java.util.Map;

@Data
public class SearchExamRequest extends SearchRequest {
    private String name;
}
