package com.proiectps.BookShop.model;

import com.proiectps.BookShop.repository.AdminRepository;
import com.proiectps.BookShop.repository.ClientRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@Data
public class User { //am nevoie de o metoda care sa-mi salveze in DB si la client si la admin
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    //@NotBlank
    private String firstName;
    //@NotBlank
    private String lastName;
    //@NotBlank
    private String email;
    //@NotBlank
    private String password;
    //@NotBlank
    private Role role;
    private boolean userLogged;

    public User(){
        this.userLogged = false;
    }

    public void saveUser(AdminRepository adminRepository, ClientRepository clientRepository){
        if(this.role == Role.ADMIN){
            Admin admin = new Admin();
            admin.setFirstName(this.firstName);
            admin.setLastName(this.lastName);
            admin.setEmail(this.email);
            admin.setPassword(this.password);
            adminRepository.save(admin);
        }
        else{
            Client client = new Client();
            client.setFirstName(this.firstName);
            client.setLastName(this.lastName);
            client.setEmail(this.email);
            client.setPassword(this.password);
            clientRepository.save(client);
        }
    }

    public Client createClient(){ //trebuie sa vad daca le mai folosesc
        Client client = new Client();
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setEmail(this.email);
        client.setPassword(this.password);
        return client;
    }

    public Admin createAdmin(){ //trebuie sa vad daca le mai folosesc
        Admin admin = new Admin();
        admin.setFirstName(this.firstName);
        admin.setLastName(this.lastName);
        admin.setEmail(this.email);
        admin.setPassword(this.password);
        return admin;
    }

    public String toString(){
        return "Userul " + id + " cu numele " + firstName + " " + lastName + ", cu email-ul " + email + " si parola " + password;
    }
}