package com.example.sandav.infrastructure.exception;

public class StarshipNotFoundException extends RuntimeException{
    public StarshipNotFoundException(Integer id){
        super("There is not a starship with id: "+id);
    }

}
