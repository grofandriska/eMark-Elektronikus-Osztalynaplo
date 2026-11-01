package com.codecool.vizsgaremek.exception;



public class ClassException extends RuntimeException{

    public ClassException(Long id) {
        super("Could not find the given class in the school by the given id : " + id);
    }

}
