package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.Type;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public interface BookService { //nu e finalizata
    Book insertBook(Book book);
    Book updateBookByPrice(Book book, Float price);
    Book updateBookByStock(Book book, int stock);
    Book deleteBook(Book book);
    Optional<Book> deleteById(Long id);
    Book deleteBookByName(String name);
    Optional<Book> findById(Long id);
    List<Book> searchFromName(String name);
    List<Book> searchFromAuthor(String author);
    List<Book> searchFromType(Type type);
    List<Book> findAll();

    List<Book> findByClient(Client client);

    List<Book> sortBooks();
}

