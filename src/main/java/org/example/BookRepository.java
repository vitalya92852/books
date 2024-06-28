package org.example;

import org.example.exception.BookAlreadyExistException;
import org.example.exception.BookFilterNotFountException;
import org.example.exception.BookListIsEmptyException;
import org.example.exception.BookNotFoundException;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepository {

    private Long autoId;
    private Set<Book> bookList;

    public BookRepository() {
        this.autoId = 0L;
        this.bookList = new HashSet<>();
    }

    public Set<Book> getBookList() {
        return bookList;
    }

    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }

    public void createBook(String title,String author,int year){
        autoId++;
        Book book = new Book(title,author,year,autoId);
        book.setTitle(title);
        book.setId(autoId);
        book.setYear(year);
        book.setAuthor(author);
        this.addBook(book);
    }

    public void addBook(Book book){
        if (!bookList.contains(book)) {
            for(Book book1:bookList){
                System.out.println(book1.getAuthor());
                System.out.println(book1.getId());
            }
            bookList.add(book);
        } else {
            throw new BookAlreadyExistException();
        }
    }

    public void deleteBook(Long id){
        Book book = this.getBookList().stream()
                .filter(bookItem-> bookItem.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new BookNotFoundException(id));
        bookList.remove(book);
        System.out.println("Книга успешно удалена");
    }

    public void showAllBooks(BookRepository bookRepository){

        List<String[]> bookInfoList = bookRepository.getBookList().stream()
                .map(book -> new String[]{
                        String.valueOf(book.getId()),
                        book.getTitle(),
                        book.getAuthor(),
                        String.valueOf(book.getYear())
                })
                .toList();
        System.out.println("Book Info:");
        for (String[] bookInfo : bookInfoList) {
            System.out.print("ID: " + bookInfo[0] + ", ");
            System.out.print("Title: " + bookInfo[1] + ", ");
            System.out.print("Author: " + bookInfo[2] + ", ");
            System.out.println("Year: " + bookInfo[3]);
        }

    }

    public void findBooksByFilter(String title,String author,Integer year){
        BookRepository bookRepositoryFilter = new BookRepository();
        bookRepositoryFilter.setBookList(
                bookList.stream()
                        .filter(book -> (book.getTitle().equalsIgnoreCase(title)) ||
                                (book.getAuthor().equalsIgnoreCase(author)) ||
                                (((Integer) book.getYear()).equals(year)))
                        .collect(Collectors.toSet())
        );
        if(bookRepositoryFilter.getBookList().isEmpty()){
            throw new BookFilterNotFountException();
        }


        showAllBooks(bookRepositoryFilter);
    }

}
