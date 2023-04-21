package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Role;
import com.proiectps.BookShop.model.Type;
import com.proiectps.BookShop.model.User;
import com.proiectps.BookShop.repository.BookRepository;
import com.proiectps.BookShop.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookServiceTest {
    private static final String NAME = "Mandrie si prejudecata";
    private static final String NAME2 = "Notre-Dame de Paris";
    private static final String NAME_NOT = "Nu avem acest nume";
    private static final String AUTHOR = "Jane Austin";
    private static final String AUTHOR2 = "Victor Hugo";
    private static final String AUTHOR_NOT = "Nu avem acest autor";
    private static final Type TYPE = Type.ROMANCE;
    private static final Type TYPE_NOT = Type.SF;

    private static final Float PRICE = 34.6f;
    private static final int STOCK = 10;

    private BookServiceImpl bookServiceImpl;
    @Mock
    private BookRepository bookRepository;
    private Book book;
    private Book book2;
    private Book book3;
    private List<Book> books;

    @BeforeEach
    void init(){
        initMocks(this);
        book = new Book();
        books = new ArrayList<Book>();
        book.setId(3L);
        book.setName(NAME);
        book.setAuthor(AUTHOR);
        book.setType(TYPE);
        book.setPrice(PRICE);
        book.setStock(STOCK);
        books.add(book);

        book2 = new Book();
        book2.setName(NAME2);
        book2.setAuthor(AUTHOR2);

        book3 = new Book();
        book3.setId(3L);
        book3.setName(NAME);
        book3.setAuthor(AUTHOR);
        book3.setPrice(PRICE);
        book3.setStock(STOCK);

        Book bookAux = new Book();
        bookAux.setName(NAME2);
        bookAux.setAuthor(AUTHOR2);
        //System.out.println(bookRepository.findAllByName(NAME).get(0));

        when(bookRepository.findAllByName(NAME)).thenReturn(books);
        when(bookRepository.findAllByAuthor(AUTHOR)).thenReturn(books);
        when(bookRepository.findAllByType(TYPE)).thenReturn(books);
        when(bookRepository.save(bookAux)).thenReturn(book2);
        when(bookRepository.save(book)).thenReturn(book3); //ma ucide aceasta chestie
        //when(bookRepository.delete(book)).thenReturn(book3);
        doNothing().when(bookRepository).delete(book);
        when(bookRepository.findById(3L)).thenReturn(Optional.ofNullable(book3));
    }

    @Test
    void giventExistingName_whenFindByAllName_thenFindFirstOne(){
        bookServiceImpl = new BookServiceImpl(bookRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        List<Book> books = bookServiceImpl.searchFromName(NAME);

        assertNotNull(books);
        assertEquals(NAME, books.get(0).getName());
    }

    @Test
    void givenNonExistingName_whenFindAllByName_thenFindFirstOne(){
        when(bookRepository.findAllByName(NAME_NOT)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            bookServiceImpl.searchFromName(NAME_NOT);
        });
    }

    @Test
    void giventExistingAuthor_whenFindByAllAuthor_thenFindFirstOne(){
        bookServiceImpl = new BookServiceImpl(bookRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByAuthor(AUTHOR).toString());

        //bookServiceImpl.searchFromName(NAME);

        List<Book> books = bookServiceImpl.searchFromAuthor(AUTHOR);

        assertNotNull(books);
        assertEquals(AUTHOR, books.get(0).getAuthor());
    }

    @Test
    void givenNonExistingAuthor_whenFindAllByAuthor_thenFindFirstOne(){
        when(bookRepository.findAllByAuthor(AUTHOR_NOT)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            bookServiceImpl.searchFromAuthor(AUTHOR_NOT);
        });
    }

    @Test
    void giventExistingType_whenFindByAllType_thenFindFirstOne(){
        bookServiceImpl = new BookServiceImpl(bookRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByType(TYPE).toString());

        //bookServiceImpl.searchFromName(NAME);

        List<Book> books = bookServiceImpl.searchFromType(TYPE);

        assertNotNull(books);
        assertEquals(TYPE, books.get(0).getType());
    }

    @Test
    void givenNonExistingType_whenFindAllByType_thenFindFirstOne(){
        when(bookRepository.findAllByType(TYPE_NOT)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            bookServiceImpl.searchFromType(TYPE_NOT);
        });
    }

    @Test
    void givenExistingBook_whenInsertBook_thenFindOne(){
        bookServiceImpl = new BookServiceImpl(bookRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Book bookAux = new Book();
        bookAux.setName(NAME2);
        bookAux.setAuthor(AUTHOR2);

        Book book = bookServiceImpl.insertBook(bookAux);

        assertNotNull(book);
        assertEquals(NAME2, book.getName());
    }

    @Test
    void givenNonExistingBook_whenInsertBook_thenFindFirstOne(){
        Book bookAux = new Book();
        //bookAux.setId(bookRepository.findByName(NAME).getId());
        bookAux.setName(NAME);
        bookAux.setAuthor(AUTHOR);
        when(bookRepository.save(bookAux)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            bookServiceImpl.insertBook(bookAux);
        });
    }

    @Test
    void givenExistingBook_whenUpdateBookByPrice_thenFindOne(){ //ce imi plac mie erorile de genul
        bookServiceImpl = new BookServiceImpl(bookRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Book bookAux = new Book();
        bookAux.setId(3L);
        bookAux.setName(NAME);
        bookAux.setAuthor(AUTHOR);

        Book book = bookServiceImpl.updateBookByPrice(bookAux, PRICE);

        assertNotNull(book);
        assertEquals(NAME, book.getName());
    }

    @Test
    void givenNonExistingBook_whenUpdateBookByPrice_thenFindFirstOne(){
        Book bookAux = new Book();
        //Book bookAux2 = new Book();
        //bookAux.setId(bookRepository.findByName(NAME).getId());
        bookAux.setName(NAME_NOT);
        //bookAux.setName(NAME_NOT);
        bookAux.setAuthor(AUTHOR);
        when(bookRepository.save(bookAux)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            bookServiceImpl.updateBookByPrice(bookAux, PRICE);
        });
    }


    @Test
    void givenExistingBook_whenUpdateBookByStock_thenFindOne(){
        bookServiceImpl = new BookServiceImpl(bookRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Book bookAux = new Book();
        bookAux.setId(3L);
        bookAux.setName(NAME);
        bookAux.setAuthor(AUTHOR);

        Book book = bookServiceImpl.updateBookByStock(bookAux, STOCK);

        assertNotNull(book);
        assertEquals(NAME, book.getName());
    }

    @Test
    void givenNonExistingBook_whenUpdateBookByStock_thenFindFirstOne(){
        Book bookAux = new Book();
        //Book bookAux2 = new Book();
        //bookAux.setId(bookRepository.findByName(NAME).getId());
        bookAux.setName(NAME_NOT);
        //bookAux.setName(NAME_NOT);
        bookAux.setAuthor(AUTHOR);
        when(bookRepository.save(bookAux)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            bookServiceImpl.updateBookByStock(bookAux, STOCK);
        });
    }

    @Test
    void doNothingBook_whenDelete(){
        bookServiceImpl = new BookServiceImpl(bookRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Book bookAux = new Book();
        bookAux.setId(3L);
        bookAux.setName(NAME);
        bookAux.setAuthor(AUTHOR);

        Book book = bookServiceImpl.deleteBook(bookAux);

        assertNotNull(book);
        //assertEquals(NAME, book.getName());
    }


    @Test
    void notDoNothingBook_whenDelete(){
        Book bookAux = new Book();
        bookAux.setId(6L);
        //Book bookAux2 = new Book();
        //bookAux.setId(bookRepository.findByName(NAME).getId());
        bookAux.setName(NAME_NOT);
        //bookAux.setName(NAME_NOT);
        bookAux.setAuthor(AUTHOR);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            bookServiceImpl.deleteBook(bookAux);
        });
    }




}
