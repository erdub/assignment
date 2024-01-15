package com.example.assignment.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Data not found");
    }
}
