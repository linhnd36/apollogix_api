package com.apollogix.managerskill.constants;

import lombok.Getter;

/**
 * Enum class for role user
 */
@Getter
public enum Role {

    ROLE_STUDENT(0, "ROLE_STUDENT"),
    ROLE_TEACHER(1, "ROLE_TEACHER");

    private final Integer i;
    private final String role;

    Role(Integer i, String role) {
        this.i = i;
        this.role = role;
    }
}
