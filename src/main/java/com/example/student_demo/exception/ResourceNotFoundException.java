package com.example.student_demo.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }
}
