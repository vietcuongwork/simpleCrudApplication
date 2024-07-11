package com.vietcuong.simpleCrudApplication.exception;

public class BookExistedException extends RuntimeException{
    public BookExistedException(){
        super("Book existed");
    }
}
