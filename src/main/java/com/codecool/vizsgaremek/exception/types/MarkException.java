package com.codecool.vizsgaremek.exception;

public class MarkException  extends RuntimeException {

    public MarkException(Long id) {
        super("Could not find the given Mark at id :"+id);
    }
}
