package com.proiectps.BookShop.validator;

import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ClientValidator {
    private static final Pattern EMAIL_REGEX = Pattern.compile("^(.+)@(.+)$");
    //private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,}$");

    public void validateEmail(String email) throws WrongAndNullException {
        Matcher emailMatcher = EMAIL_REGEX.matcher(email);
        if (!emailMatcher.matches()) {
            throw new WrongAndNullException("Wrong email");
        }
    }


    private void validatePassword(String password) throws WrongAndNullException {
        if(password == null || password.isBlank() || password.isEmpty()){
            throw new WrongAndNullException("Null password");
        }
    }


    private void validateFirstName(String name) throws WrongAndNullException {
        if(name == null || name.isBlank() || name.isEmpty()){
            throw new WrongAndNullException("Null firstName");
        }
    }

    private void validateLastName(String name) throws WrongAndNullException {
        if(name == null || name.isBlank() || name.isEmpty()){
            throw new WrongAndNullException("Null lastName");
        }
    }

    public void validateClient(Client client) throws WrongAndNullException {
        if(client == null){
            throw new NullPointerException("Null client");
        }
        validateFirstName(client.getFirstName());
        validateLastName(client.getLastName());
        validateEmail(client.getEmail());
        validatePassword(client.getPassword());
    }
}
