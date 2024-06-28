package org.example.exception;

public class BookAlreadyExistException extends RuntimeException{
    public BookAlreadyExistException(){
        super("Такая книга уже есть в вашем списке");
    }
}
