package com.codecool.vizsgaremek.exception;

public class TeacherException extends RuntimeException {

    public TeacherException(Long id) {
        super("Could not find Teacher by the given id: " + id);

    }
}