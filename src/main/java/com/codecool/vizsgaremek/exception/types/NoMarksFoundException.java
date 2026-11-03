package com.codecool.vizsgaremek.exception.types;

public class NoMarksFoundException extends RuntimeException {

    public NoMarksFoundException(Long studentId) {
        super("No marks found for student with id: " + studentId);
    }
}