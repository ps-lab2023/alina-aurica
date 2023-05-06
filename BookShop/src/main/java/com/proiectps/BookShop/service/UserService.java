package com.proiectps.BookShop.service;

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
    User logIn(String email, String password);
    User logOut(String email); //neimplementat
    User register(User user);

    User changeEmail(User user, String email);
    User changePassword(User user, String password); //va fi facuta pt partea de update data

    User deleteUser(User user);
}
