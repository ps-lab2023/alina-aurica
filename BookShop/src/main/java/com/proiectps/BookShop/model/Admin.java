package com.proiectps.BookShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long idAdmin;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    /*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Book> books;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "idClient")
    private List<Client> clients;
     */

//    public String generateBill(Client client){ //asta va suferi modificari
//        String str = "";
//        for(Book b: client.getBooks()){
//            str += b.getId() + " " + b.getName() + " " + b.getAuthor() + " " + b.getPrice() + " ";
//        }
//        return "Clientul cu id-ul " + client.getIdClient() + " a achizitionat urmatoarele carti " + str + ". " + "\n" +
//                "Total de plata " + client.calculatePrice();
//    }

}
