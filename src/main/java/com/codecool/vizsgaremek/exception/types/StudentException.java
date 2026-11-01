package com.codecool.vizsgaremek.exception;

public class StudentException extends RuntimeException {

    public StudentException(Long id) {
        super("Could not find the given Student in the class by the given id :" + id);
    }

}
