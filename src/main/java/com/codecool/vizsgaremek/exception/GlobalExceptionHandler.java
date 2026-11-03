package com.codecool.vizsgaremek.exception;

import com.codecool.vizsgaremek.exception.types.ClassException;
import com.codecool.vizsgaremek.exception.types.MarkException;
import com.codecool.vizsgaremek.exception.types.StudentException;
import com.codecool.vizsgaremek.exception.types.TeacherException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
//TODO:CHECKED VS UNCHECKED
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            MarkException.class,
            StudentException.class,
            TeacherException.class,
            ClassException.class
    })
    public ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpected(Exception ex) {
        log.error("Unexpected error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error"));
    }

    // Spring's own errors (bad path variable, missing body, wrong method...)
    // keep their correct status (e.g. 400), but get the same JSON format.
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        ErrorResponse error = new ErrorResponse(status.value(), status.getReasonPhrase());
        return new ResponseEntity<>(error, headers, status);
    }
}