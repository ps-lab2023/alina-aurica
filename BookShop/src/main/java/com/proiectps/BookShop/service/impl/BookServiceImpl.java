package com.proiectps.BookShop.service.impl;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.Type;
import com.proiectps.BookShop.repository.BookRepository;
import com.proiectps.BookShop.service.BookService;
import com.proiectps.BookShop.validator.BookValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Transactional
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private final BookValidator bookValidator = new BookValidator();

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    @Override
    public Book insertBook(Book book) {
        try {
            bookValidator.validateBook(book);
            if(bookRepository.findByNameAndAuthor(book.getName(), book.getAuthor()) == null) {
                bookRepository.save(book);
                return book;
            }
            else
                return null;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book updateBookByPrice(Book book, Float price) {
        try {
            //bookValidator.validateBook(book);
            Book updateBook = bookRepository.findByName(book.getName());
            bookValidator.validateBook(updateBook);
            updateBook.setPrice(price);
            bookRepository.save(updateBook);
            return updateBook;

            /*
            if(updateBook != null){
                updateBook.setPrice(price);
                bookRepository.save(updateBook);
            }
            return updateBook;
            */
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Book updateBookByStock(Book book, int stock) {
        try {
            //bookValidator.validateBook(book);
            Book updateBook = bookRepository.findByName(book.getName());
            bookValidator.validateBook(updateBook);
            updateBook.setStock(stock);
            bookRepository.save(updateBook);
            return updateBook;
            /*
            if(updateBook != null){
                updateBook.setStock(stock);
                bookRepository.save(updateBook);
            }
            */
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Book deleteBook(Book book) { // merge
        try {
            bookValidator.validateBook(book);
            Book deleteBook = bookRepository.findByName(book.getName());
            bookValidator.validateBook(deleteBook);
            bookRepository.delete(deleteBook);
            return deleteBook;
            /*
            if(deleteBook != null)
                bookRepository.delete(deleteBook);
            return deleteBook;
             */
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Book> deleteById(Long id) {
            //bookValidator.validateBook(book);
            Optional<Book> deleteBook = bookRepository.findById(id);
            //bookValidator.validateBook(deleteBook);
            bookRepository.deleteById(id);
            return deleteBook;
            /*
            if(deleteBook != null)
                bookRepository.delete(deleteBook);
            return deleteBook;
             */

    }

    @Transactional
    @Override
    public Book deleteBookByName(String name) { //nu merge

        try {
            Book deleteBook = bookRepository.findByName(name);
            bookValidator.validateBook(deleteBook);
            bookRepository.deleteBookByName(name);
            return deleteBook;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> searchFromName(String name) {
        //System.out.println(name);
        //System.out.println(bookRepository.findAllByName(name).toString());
        return bookRepository.findAllByName(name);
    }

    @Override
    public List<Book> searchFromAuthor(String author) {
        List<Book> newList = bookRepository.findAllByAuthor(author);
        //System.out.println(newList);
        return newList;
    }

    @Override
    public List<Book> searchFromType(Type type) {
        return bookRepository.findAllByType(type);
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public List<Book> findByClient(Client client) {
        return bookRepository.findAllByClientsContains(client);
    }

    @Override
    public List<Book> sortBooks(){
        List<Book> sortedList = findAll();
        Collections.sort(sortedList);
        return sortedList;
    }


}
