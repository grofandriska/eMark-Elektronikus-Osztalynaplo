package com.codecool.vizsgaremek.exception.types;



public class ClassException extends RuntimeException{

    public ClassException(Long id) {
        super("Could not find the given class in the school by the given id : " + id);
    }

    public ClassException(String name) {
        super("Could not find the given class in the school by the given name : " + name);
    }
}
