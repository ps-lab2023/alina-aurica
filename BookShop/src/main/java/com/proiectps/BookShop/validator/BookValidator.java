package com.proiectps.BookShop.validator;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Type;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {
    private void validateName(String name) throws WrongAndNullException {
        if(name == null || name.isBlank() || name.isEmpty()){
            throw new WrongAndNullException("Null name");
        }
    }

    public void validateAuthor(String author) throws WrongAndNullException {
        if(author == null || author.isBlank() || author.isEmpty()){
            throw new WrongAndNullException("Null author");
        }
    }

    public void validateType(Type type) throws WrongAndNullException {
        if(type == null){
            throw new WrongAndNullException("Null type");
        }
    }

    public void validateBook(Book book) throws WrongAndNullException {
        if(book == null){
            throw new NullPointerException("Null book");
        }
        validateName(book.getName());
        validateAuthor(book.getAuthor());
        validateType(book.getType());

    }


}
