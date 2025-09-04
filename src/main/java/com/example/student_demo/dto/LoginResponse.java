package com.example.student_demo.dto;

public class LoginResponse {
    private String token;
    private String role;

    public LoginResponse(String token,String role)
    {
        this.token=token;
        this.role=role;
    }
}
