package com.proiectps.BookShop.controller;


import com.proiectps.BookShop.DTO.BillDTO;
import com.proiectps.BookShop.DTO.BookDTO;
import com.proiectps.BookShop.DTO.ClientDTO;
import com.proiectps.BookShop.DTO.UserDTO;
import com.proiectps.BookShop.model.Bill;
import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.service.BillService;
import com.proiectps.BookShop.service.BookService;
import com.proiectps.BookShop.service.ClientService;
import com.proiectps.BookShop.service.impl.EmailServiceImpl;
import com.proiectps.BookShop.validator.ClientValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import jakarta.mail.MessagingException;
import jdk.swing.interop.SwingInterOpUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private EmailServiceImpl emailSenderService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BillService billService;

    @Autowired
    private ModelMapper modelMapper;

    private final ClientValidator clientValidator = new ClientValidator();

    @GetMapping("/findByEmail")
    public ResponseEntity findClientByEmail(@RequestParam String email){
        Client client = clientService.findByEmail(email);
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
    }

    @GetMapping("/findByEmailAndPassword")
    public ResponseEntity findClientByEmailAndPassword(@RequestParam String email, String password){
        Client client = clientService.findByEmailAndPassword(email, password);
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
    }

    @DeleteMapping("/delete")
    public ClientDTO deleteClient(@RequestBody Client client){
        Client client1 = clientService.deleteClient(client);
        ClientDTO clientDTO = modelMapper.map(client1, ClientDTO.class);

        return clientDTO;
    }

    @PutMapping("/updateEmail")
    public ClientDTO updateClientByEmail(@RequestBody Client client, @RequestParam String email){

        Client client1 = clientService.updateClientByEmail(client, email);
        ClientDTO clientDTO = modelMapper.map(client1, ClientDTO.class);
        return clientDTO;
    }

    @PutMapping("/insert")
    public ClientDTO insertAdmin(@RequestBody Client client){

        try {
            Client client1 = clientService.insertClient(client);
            clientValidator.validateClient(client1);
            ClientDTO clientDTO = modelMapper.map(client1, ClientDTO.class);
            return clientDTO;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/addInCart{name}")
    public ResponseEntity addInCart(@RequestBody UserDTO userDTO, @PathVariable String name){
        Client client = clientService.findByEmail(userDTO.getEmail());
        List<Book> books = clientService.addInCart(name, client);
        List<BookDTO> booksDTO =  books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(booksDTO);
    }

    @PostMapping("/deleteFromCart{name}")
    public ResponseEntity deleteFromCart(@RequestBody UserDTO userDTO, @PathVariable String name){
        Client client = clientService.findByEmail(userDTO.getEmail());
        List<Book> books = clientService.deleteFromCart(name, client);
        List<BookDTO> booksDTO =  books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(booksDTO);
    }

    @PostMapping("/generateBill")
    public ResponseEntity generateBill(@RequestBody UserDTO userDTO) throws MessagingException {
        Client client = clientService.findByEmail(userDTO.getEmail());
        Bill bill = new Bill();
        try {
            bill = billService.generateBill(client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BillDTO billDTO = modelMapper.map(bill, BillDTO.class);
        //EmailServiceImpl.run(userDTO.getEmail());


        emailSenderService.sendMailWithAttachment(userDTO.getEmail(),
                "This is email body.",
                "This email subject", "" +
                        "C:\\Users\\acasa\\Downloads\\BookShop\\BookShop\\Bill");

        return ResponseEntity.status(HttpStatus.OK).body(billDTO);
    }


}
