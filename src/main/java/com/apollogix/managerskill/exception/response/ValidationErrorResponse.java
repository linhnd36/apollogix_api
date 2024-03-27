package com.apollogix.managerskill.exception.response;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ValidationErrorResponse {

    private String field;
    private Object rejectedValue;
    private String message;

}
