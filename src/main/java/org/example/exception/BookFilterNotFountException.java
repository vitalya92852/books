package org.example.exception;

public class BookFilterNotFountException extends RuntimeException{
    public BookFilterNotFountException(){
        super("Фильтр не нашел книг");
    }
}
