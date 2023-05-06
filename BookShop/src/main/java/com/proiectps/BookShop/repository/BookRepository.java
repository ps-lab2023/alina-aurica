package com.proiectps.BookShop.repository;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByName(String name);
    Book findByAuthor(String author);
    Book findByType(Type type);

    //Book updateByPrice(Float price);
    void delete(Book book);

    Book findByNameAndAuthor(String name, String author);

    Book deleteBookByName(String name); //de ce nu vrei sa mergi, ca nu inteleg
    void deleteById(Long id);

    List<Book> findAllByName(String name);
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByType(Type type);

    List<Book> findAllByClientsContains(Client client);
}
