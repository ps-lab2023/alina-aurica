package com.proiectps.BookShop.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@Getter
public class Client { //cred ca pot scapa de lista de carti de aici
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Setter
    private Long idClient;

    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String email;
    @Setter
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Book> books1;

    @Setter
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Bill bill;

    /*
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
     */

    public Client(){
    }

    public List<Book> addInCart(Book book){
        books1.add(book);
        return books1;
    }

    public List<Book> deleteFromCart(Book book){
        books1.remove(book);
        return books1;
    }

    public Float calculatePrice(){
        Float sum = 0.0f;
        for(Book b: books1){
            sum += b.getPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                //", books=" + books +
                '}';
    }
}
