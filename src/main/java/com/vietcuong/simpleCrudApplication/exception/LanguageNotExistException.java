package com.vietcuong.simpleCrudApplication.exception;

public class LanguageNotExistException extends RuntimeException{
    public LanguageNotExistException(){
        super("Language doesn't exist");
    }
}
