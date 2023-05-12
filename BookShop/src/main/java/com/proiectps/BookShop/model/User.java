package com.proiectps.BookShop.model;

import com.proiectps.BookShop.repository.AdminRepository;
import com.proiectps.BookShop.repository.ClientRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements UserDetails {
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

    @Enumerated(EnumType.STRING)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}