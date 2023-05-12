package com.proiectps.BookShop.service;

import com.proiectps.BookShop.controller.auth.AuthenticationResponse;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    User findById(Long id);

    User findByEmailAndPassword(String email, String password);

    List<User> findAll();

    User findByEmail(String email);

    AuthenticationResponse logIn(String email, String password);

    //    User logIn(String email, String password);
    User logOut(String email);

    //    User register(User user);
    AuthenticationResponse register(User user);

    AuthenticationResponse registerAdmin(User user);

    User changeEmail(User user, String email);

    AuthenticationResponse changePassword(User user, String password); //va fi facuta pt partea de update data

    User deleteUser(User user);

    void saveUserToXML(Long id);
}
