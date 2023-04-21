package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientService {
    Client findByEmailAndPassword(String email, String password);
    Client findByEmail(String email);

    Client insertClient(Client client);
    Client updateClientByEmail(Client client, String email);
    Client deleteClient(Client client);

    List<Book> addInCart(String name, Client client);
    List<Book> deleteFromCart(String name, Client client);
}
