package com.apollogix.managerskill.request;


import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String password;
    private String name;
}
