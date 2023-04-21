package com.proiectps.BookShop.service.impl;

import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.User;
import com.proiectps.BookShop.repository.AdminRepository;
import com.proiectps.BookShop.repository.ClientRepository;
import com.proiectps.BookShop.repository.UserRepository;
import com.proiectps.BookShop.service.UserService;
import com.proiectps.BookShop.validator.UserValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class UserServiceImpl implements UserService { //cand fac update si delete si imi modifice si ce am in baza de date de client si admin
    @Autowired
    private UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;

    private final UserValidator userValidator = new UserValidator();

    public UserServiceImpl(UserRepository userRepository, ClientRepository clientRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public User findById(Long id) { //aici chiar ma distrez :)))
        return userRepository.findById(id).get();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User logIn(String email, String password) {
        try {
            User logInUser = findByEmailAndPassword(email, password);
            userValidator.validateUser(logInUser);
            return logInUser;
        } catch (WrongAndNullException e) {
            //throw new RuntimeException(e);
            System.out.println("Invalid user - email/password incorrect");
            return null;
        }

        /*
        if(findByEmailAndPassword(user) == null){
            System.out.println("Email si/sau parola incorecte");
            return null;
        }
        else {
            System.out.println("Userul s-a logat cu succes!");
            return user;
        }
         */
    }

    @Override
    public User logOut() {//va fi implementata

        return null;
    }

    @Override
    public User register(User user) {
        try {
            userValidator.validateUser(user);
            user.saveUser(adminRepository, clientRepository);
            System.out.println("Userul s-a inregistrat cu succes!");
            return userRepository.save(user);
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User changeEmail(User user, String email) {
        try {
            userValidator.validateUser(user);
            userValidator.validateEmail(email);
            User updateUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            userValidator.validateUser(updateUser);
            updateUser.setEmail(email);
            //userRepository.save(updateUser);
            updateUser.saveUser(adminRepository, clientRepository);
            return updateUser;
            /*
            if(updateUser != null){
                updateUser.setEmail(email);
                userRepository.save(updateUser);
                return updateUser;
            }
             */
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User deleteUser(User user) {
        try {
            userValidator.validateUser(user);
            User deleteUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            userValidator.validateUser(deleteUser);
            userRepository.delete(deleteUser);
            return deleteUser;
            /*
            if(deleteUser != null){
                userRepository.delete(deleteUser);
                return deleteUser;
            }
             */
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

        //return null;
    }


}
