package com.apollogix.managerskill.request;


import lombok.Data;

@Data
public class UserUpdateRequest {
    private String password;
    private String name;
}
