package com.proiectps.BookShop.service.impl;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.repository.BookRepository;
import com.proiectps.BookShop.repository.ClientRepository;
import com.proiectps.BookShop.service.BookService;
import com.proiectps.BookShop.service.ClientService;
import com.proiectps.BookShop.validator.ClientValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    ClientValidator clientValidator = new ClientValidator();

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findByEmailAndPassword(String email, String password) {
        return clientRepository.findClientByEmailAndPassword(email, password);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    @Override
    public Client insertClient(Client client) {
        try {
            clientValidator.validateClient(client);
            if (clientRepository.findClientByEmailAndPassword(client.getEmail(), client.getPassword()) == null) {
                //clientRepository.save(client);
                return clientRepository.save(client);
            } else return null;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Client updateClientByEmail(Client client, String email) {
        try {
            clientValidator.validateClient(client);
            clientValidator.validateEmail(email);
            Client updateClient = clientRepository.findClientByEmailAndPassword(client.getEmail(), client.getPassword());
            clientValidator.validateClient(updateClient);
            updateClient.setEmail(email);
            // clientRepository.save(updateClient);
            /*
            if(updateClient != null){
                updateClient.setEmail(email);
                clientRepository.save(updateClient);
            }
             */
            return clientRepository.save(updateClient);
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Client deleteClient(Client client) {
        try {
            clientValidator.validateClient(client);
            Client deleteClient = clientRepository.findClientByEmailAndPassword(client.getEmail(), client.getPassword());
            clientValidator.validateClient(deleteClient);
            clientRepository.delete(deleteClient);
            /*
            if(deleteClient != null)
                clientRepository.delete(deleteClient);
             */
            return deleteClient;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Book> addInCart(String name, Client client) { //trebuie sa lucram pe asta
        Book book = bookRepository.findByName(name);
        Client foundClient = clientRepository.findClientByEmail(client.getEmail());
        if (book != null && book.getStock() != 0) {
            book.setStock(book.getStock() - 1);
            List<Client> clients = book.getClients();
            clients.add(client);
            book.setClients(clients);
            bookRepository.save(book);

            List<Book> books = foundClient.getBooks1();
            books.add(book);
            foundClient.setBooks1(books);
            clientRepository.save(foundClient);

        }
        return bookService.findByClient(client);
    }

    public List<Book> deleteFromCart(String name, Client client) {
        List<Book> books = new ArrayList<Book>();
        for (Book b : bookService.findByClient(client))
            books.add(b);

        for (Book b : books) {
            if (b.getName().equals(name)) {
                //client.deleteFromCart(b);
                Book book = bookRepository.findByName(name);
                Client foundClient = clientRepository.findClientByEmail(client.getEmail());
                book.setStock(book.getStock() + 1);
                List<Client> clients = book.getClients();
                clients.remove(foundClient);
                book.setClients(clients);
                bookRepository.save(book);


                List<Book> books1 = foundClient.getBooks1();
                books1.remove(book);
                foundClient.setBooks1(books1);
                clientRepository.save(foundClient);

            }
        }
        return bookService.findByClient(client);
    }
}
