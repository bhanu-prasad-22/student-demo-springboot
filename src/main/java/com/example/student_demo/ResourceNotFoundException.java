package com.example.student_demo;

public class ResourceNotFoundException extends RuntimeException{
    ResourceNotFoundException(String msg)
    {
        super(msg);
    }
}
