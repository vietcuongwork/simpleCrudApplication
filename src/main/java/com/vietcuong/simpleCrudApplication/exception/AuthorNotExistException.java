package com.vietcuong.simpleCrudApplication.exception;

public class AuthorNotExistException extends RuntimeException{
    public AuthorNotExistException(){
        super("Author doesn't exist");
    }
}
