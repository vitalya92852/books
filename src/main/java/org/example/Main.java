package org.example;

import org.example.exception.BookListIsEmptyException;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        while (true) {
            System.out.println("Меню \n 1.Добавить книгу \n 2.Удалить книгу \n 3.Найти по фильтру \n 4.Все книги");
            Scanner scanner = new Scanner(System.in);
            String selectMenuNum = scanner.nextLine();
            try {
                switch (Integer.parseInt(selectMenuNum)){
                    case 1:
                        System.out.println("Введите имя новой книги");
                        String inputTitle = scanner.nextLine();

                        System.out.println("Введите год новой книги");
                        int inputYear;
                        do {
                            try {
                                inputYear = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (NumberFormatException e){
                                System.out.println("Введено неккоректное число, повторите попытку");
                            }
                        } while (true);

                        System.out.println("Введите автора новой книги");
                        String inputAuthor = scanner.nextLine();

                        bookRepository.createBook(inputTitle,inputAuthor,inputYear);
                        break;
                    case 2:
                        if(bookRepository.getBookList().isEmpty()){
                            throw new BookListIsEmptyException();
                        }

                        Long bookId = 0L;
                        System.out.println("Выберите id книги");
                        bookRepository.showAllBooks(bookRepository);
                        do {
                            try {
                                bookId = Long.parseLong(scanner.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Введено неккоректное число, повторите попытку");
                            }
                        }while (true);


                        bookRepository.deleteBook(bookId);
                        break;
                    case 3:
                        System.out.println("Введите имя");
                        String inputFilterTitle = scanner.nextLine();

                        System.out.println("Введите автора");
                        String inputFilterAuthor = scanner.nextLine();

                        System.out.println("Введите год выпуска книги");
                        int inputSearchYear;
                        do {
                            try {
                                inputSearchYear = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (NumberFormatException e){
                                System.out.println("Введено неккоректное число, повторите попытку");
                            }
                        } while (true);


                        bookRepository.findBooksByFilter(inputFilterTitle,inputFilterAuthor,inputSearchYear);
                        break;

                    case 4:
                        bookRepository.showAllBooks(bookRepository);
                        break;
                    default:
                        System.out.println("Выберите корректный пункт меню (1-4).");
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("Введете неккоректное число");
            }
        }
    }
}