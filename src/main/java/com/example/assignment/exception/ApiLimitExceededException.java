package com.example.assignment.exception;

public class ApiLimitExceededException extends RuntimeException {
    public ApiLimitExceededException() {
        super("API limit has been exceeded, please try again later");
    }
}
