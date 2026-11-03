package com.codecool.vizsgaremek.exception.types;

public class SubjectMismatchException extends RuntimeException {

  public SubjectMismatchException(String teacherSubject, String markSubject) {
    super("Mismatch in subject: " + teacherSubject + " and mark: " + markSubject);
  }
}
