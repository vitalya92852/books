package org.example.exception;

public class BookListIsEmptyException extends RuntimeException{
    public BookListIsEmptyException(){
        super("В вашем списке нет книг");
    }
}
