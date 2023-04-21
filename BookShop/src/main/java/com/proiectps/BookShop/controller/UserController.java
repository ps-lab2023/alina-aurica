package com.proiectps.BookShop.controller;


import com.proiectps.BookShop.DTO.AdminDTO;
import com.proiectps.BookShop.DTO.ClientDTO;
import com.proiectps.BookShop.DTO.UserDTO;
import com.proiectps.BookShop.model.Admin;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.User;
import com.proiectps.BookShop.service.ClientService;
import com.proiectps.BookShop.service.UserService;
import com.proiectps.BookShop.validator.ClientValidator;
import com.proiectps.BookShop.validator.UserValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController { //sa fac o metoda in Client si Admin ca atunci cand modific ceva sa se modifice si la user
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private final UserValidator userValidator = new UserValidator();

    @GetMapping("/findById")
    public ResponseEntity getById(@RequestParam Long id){
        User user = userService.findById(id);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
    @GetMapping("/findByEmail{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        User user = userService.findByEmail(email);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/findByEmailAndPassword")
    public ResponseEntity getUserByEmailAndPassword(@RequestParam String email, String password){
        User user = userService.findByEmailAndPassword(email, password);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping("/delete")
    public UserDTO deleteUser(@RequestBody User user){
        User user1 = userService.deleteUser(user);
        UserDTO userDTO = modelMapper.map(user1, UserDTO.class);

        return userDTO;
    }

    @PutMapping("/updateEmail")
    public UserDTO updateUserByEmail(@RequestBody User user, @RequestParam String email){

        User user1 = userService.changeEmail(user, email);
        UserDTO userDTO = modelMapper.map(user1, UserDTO.class);
        return userDTO;
    }


    @PutMapping("/register")
    public UserDTO register(@RequestBody User user){ //sa ma mai uit aici
        try {
            User user1 = userService.register(user);
            userValidator.validateUser(user1);
            UserDTO userDTO = modelMapper.map(user1, UserDTO.class);
            return userDTO;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/login")
    public ResponseEntity logIn(@RequestBody UserDTO userDTO){

        User user1 = userService.logIn(userDTO.getEmail(), userDTO.getPassword());
        UserDTO userDTO1 = modelMapper.map(user1, UserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO1);
    }

}
