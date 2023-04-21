package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.Role;
import com.proiectps.BookShop.model.User;
import com.proiectps.BookShop.repository.ClientRepository;
import com.proiectps.BookShop.service.impl.BookServiceImpl;
import com.proiectps.BookShop.service.impl.ClientServiceImpl;
import com.proiectps.BookShop.service.impl.UserServiceImpl;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClientServiceTest {
    private static final String EMAIL = "mihai.pop@gmail.com";
    private static final String EMAIL2 = "mihai.pop10@gmail.com";
    private static final String EMAIL_NOT = "mihai.pop@gmail";

    private static final String PASSWORD = "aksjms";
    private static final String PASSWORD_NOT = "ana";

    private ClientServiceImpl clientServiceImpl;

    @Mock
    private ClientRepository clientRepository;

    private Client client;
    private Client client2;
    private Client client3;
    private Client client4;

    @BeforeEach
    void init(){
        initMocks(this);
        client = new Client();
        client.setIdClient(1L);
        client.setEmail(EMAIL);
        client.setPassword(PASSWORD);

        client2 = new Client();
        client2.setIdClient(2L);
        client2.setFirstName("Maria");
        client2.setLastName("Ionescu");
        client2.setEmail("maria.ionescu@gmail.com");
        client2.setPassword("maaria");

        Client clientAux = new Client();
        clientAux.setIdClient(2L);
        clientAux.setFirstName("Maria");
        clientAux.setLastName("Ionescu");
        clientAux.setEmail("maria.ionescu@gmail.com");
        clientAux.setPassword("maaria");

        client3 = new Client();
        client3.setIdClient(1L);
        client3.setEmail(EMAIL2);
        client3.setPassword(PASSWORD);

        client4 = new Client();
        client4.setIdClient(1L);
        client4.setEmail(EMAIL2);
        client4.setPassword(PASSWORD);
        //client3.setPassword("maaria");

        when(clientRepository.findClientByEmail(EMAIL)).thenReturn(client);
        when(clientRepository.findClientByEmailAndPassword(EMAIL, PASSWORD)).thenReturn(client);
        when(clientRepository.save(clientAux)).thenReturn(client2);
        when(clientRepository.save(client3)).thenReturn(client4);
        doNothing().when(clientRepository).delete(client);
        when(clientRepository.findById(1L)).thenReturn(Optional.ofNullable(client4));
    }


    @Test
    void givenExistingEmail_whenFindByEmail_thenFindOne(){
        clientServiceImpl = new ClientServiceImpl(clientRepository);
        Client clientAux = new Client();
        clientAux.setEmail(EMAIL);
        //System.out.println(clientAux);
        Client client = clientServiceImpl.findByEmail(EMAIL);
        //System.out.println(clientServiceImpl.findByEmail(EMAIL));

        assertNotNull(client);
        assertEquals(EMAIL, client.getEmail());
    }

    @Test
    void givenNotExistingEmail_whenFindByEmail_thenThrowExcepsion(){
        when(clientRepository.findClientByEmail(EMAIL_NOT)).thenReturn(null);

        User userAux = new User();
        userAux.setEmail(EMAIL_NOT);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            clientServiceImpl.findByEmail(EMAIL_NOT);
        });
    }

    @Test
    void givenExistingEmailAndPassword_whenFindByEmailAndPassword_thenFindOne(){
        clientServiceImpl = new ClientServiceImpl(clientRepository);
        Client clientAux = new Client();
        clientAux.setEmail(EMAIL);
        clientAux.setPassword(PASSWORD);
        //System.out.println(clientAux);
        Client client = clientServiceImpl.findByEmailAndPassword(clientAux.getEmail(), clientAux.getPassword());
        //System.out.println(userServiceImpl.findByEmail(userAux));

        assertNotNull(client);
        assertEquals(EMAIL, client.getEmail());
        assertEquals(PASSWORD, client.getPassword());
    }

    @Test
    void givenNotExistingEmailAndPassword_whenFindByEmailAndPassword_thenThrowExcepsion(){
        when(clientRepository.findClientByEmailAndPassword(EMAIL, PASSWORD_NOT)).thenReturn(null);

        Client clientAux = new Client();
        clientAux.setEmail(EMAIL);
        clientAux.setPassword(PASSWORD_NOT);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            clientServiceImpl.findByEmailAndPassword(clientAux.getEmail(), clientAux.getPassword());
        });
    }

    @Test
    void givenExistingClient_whenInsertClient_thenFindOne() throws WrongAndNullException {
        clientServiceImpl = new ClientServiceImpl(clientRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Client clientAux = new Client();
        clientAux.setIdClient(2L);
        clientAux.setFirstName("Maria");
        clientAux.setLastName("Ionescu");
        clientAux.setEmail("maria.ionescu@gmail.com");
        clientAux.setPassword("maaria");

        Client client = clientServiceImpl.insertClient(clientAux);

        assertNotNull(client);
        assertEquals("maria.ionescu@gmail.com", client.getEmail());
    }

    @Test
    void givenNonExistingBook_whenInsertBook_thenFindFirstOne(){
        Client clientAux = new Client();
        clientAux.setIdClient(1L);
        clientAux.setEmail(EMAIL);
        clientAux.setPassword(PASSWORD);
        when(clientRepository.save(clientAux)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            clientServiceImpl.insertClient(clientAux);
        });
    }


    @Test
    void givenExistingClient_whenUpdateClientByEmail_thenFindOne(){ //ce imi plac mie erorile de genul
        clientServiceImpl = new ClientServiceImpl(clientRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Client clientAux = new Client();
        clientAux.setIdClient(1L);
        clientAux.setEmail(EMAIL);
        Client client = clientServiceImpl.updateClientByEmail(clientAux, EMAIL2);

        assertNotNull(client);
        assertEquals(EMAIL2, client.getEmail());
    }

    @Test
    void givenNonExistingClient_whenUpdateClientByEmail_thenFindFirstOne(){
        Client clientAux = new Client();
        //Book bookAux2 = new Book();
        //bookAux.setId(bookRepository.findByName(NAME).getId());
        clientAux.setEmail(EMAIL_NOT);
        //bookAux.setName(NAME_NOT);
        when(clientRepository.save(clientAux)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            clientServiceImpl.updateClientByEmail(clientAux, EMAIL2);
        });
    }


    @Test
    void doNothingClient_whenDelete(){
        clientServiceImpl = new ClientServiceImpl(clientRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Client clientAux = new Client();
        clientAux.setIdClient(1L);
        clientAux.setEmail(EMAIL);
        clientAux.setPassword(PASSWORD);

        Client client = clientServiceImpl.deleteClient(clientAux);

        assertNotNull(client);
        //assertEquals(NAME, book.getName());
    }


    @Test
    void notDoNothingClient_whenDelete(){
        Client clientAux = new Client();
        clientAux.setIdClient(6L);
        //Book bookAux2 = new Book();
        //bookAux.setId(bookRepository.findByName(NAME).getId());
        clientAux.setEmail(EMAIL_NOT);
        //bookAux.setName(NAME_NOT);
        clientAux.setPassword(PASSWORD);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            clientServiceImpl.deleteClient(clientAux);
        });
    }

}
