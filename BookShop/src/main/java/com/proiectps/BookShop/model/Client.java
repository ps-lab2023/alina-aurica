package com.proiectps.BookShop.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@Data
public class Client { //cred ca pot scapa de lista de carti de aici
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long idClient;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "client_book",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @ToString.Exclude
    private List<Book> books1;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Bill> bills;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "idGiftCard")
    private List<GiftCard> giftCards;

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
