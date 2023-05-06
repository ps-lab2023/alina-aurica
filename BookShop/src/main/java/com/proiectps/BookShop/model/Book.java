package com.proiectps.BookShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Book implements Comparable<Book> { //cred ca pot sa scap de client de aici
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String name;

    private String author;
    private Integer stock;
    private Float price;
    private Type type;


    @ManyToMany(mappedBy = "books1")
    private List<Client> clients;

    /*
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;
    */

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", type=" + type ;}


    @Override
    public int compareTo(Book o) {
        return this.name.compareTo(o.getName());
    }
}
