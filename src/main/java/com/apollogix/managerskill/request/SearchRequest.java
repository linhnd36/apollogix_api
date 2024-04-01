package com.apollogix.managerskill.request;

import lombok.Data;

import java.util.Map;

@Data
public class SearchRequest {
    private int page = 0;
    private int size = 10;
    private Map<String, String> sort;
}
