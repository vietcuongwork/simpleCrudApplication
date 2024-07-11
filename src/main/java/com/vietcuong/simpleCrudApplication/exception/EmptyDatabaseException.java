package com.vietcuong.simpleCrudApplication.exception;

public class EmptyDatabaseException extends RuntimeException {

    public EmptyDatabaseException() {
        super("Empty database");
    }
}
