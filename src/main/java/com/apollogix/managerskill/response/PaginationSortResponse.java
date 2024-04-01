package com.apollogix.managerskill.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationSortResponse {

    private long totalElements;

    private int totalPages;

    private int currentPage;

    private int size;

    private List<?> objects;
}
