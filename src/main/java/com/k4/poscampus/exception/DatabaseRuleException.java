package com.k4.poscampus.exception;

public class DatabaseRuleException extends RuntimeException{
    public DatabaseRuleException(String mensaje){
        super(mensaje);
    }
}
