package com.vietcuong.simpleCrudApplication.exception;

public class BookNotExistException extends RuntimeException{
    public BookNotExistException(){
        super("Book doesn't exist");
    }
}
