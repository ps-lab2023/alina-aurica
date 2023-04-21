package com.proiectps.BookShop.utils;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.service.BookService;
import com.proiectps.BookShop.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillUtils {

    public static String generateBill(Client client, BookService bookService){
        StringBuilder str = new StringBuilder();
        List<Book> books = bookService.findByClient(client);
        for(Book b: books){
            str.append(b.getId()).append(" ").append(b.getName()).append(" ").append(b.getAuthor()).append(" ").append(b.getPrice()).append(" ");
        }
        return "Clientul cu id-ul " + client.getIdClient() + " a achizitionat urmatoarele carti " + str + ". " + "\n" +
                "Total de plata " + client.calculatePrice() + "\n" +
                "Data: " + LocalDateTime.now();
    }

    public static void generateBillIntoFile(Client client, BookService bookService) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Bill"));
        writer.write(generateBill(client, bookService));

        writer.close();
    }

}
