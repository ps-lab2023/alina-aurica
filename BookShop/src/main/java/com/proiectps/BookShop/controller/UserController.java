package com.proiectps.BookShop.controller;


import com.proiectps.BookShop.DTO.UserDTO;
import com.proiectps.BookShop.controller.auth.AuthenticationResponse;
import com.proiectps.BookShop.model.User;
import com.proiectps.BookShop.service.UserService;
import com.proiectps.BookShop.validator.UserValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/findAll")
    public List<UserDTO> getAllUser(){
        List<User> users = userService.findAll();
        //System.out.println("Suntem aici");
        List<UserDTO> userDTO = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTO;
    }

    @GetMapping("/xml/{id}")
    public void saveUsersToXML(@PathVariable Long id){
        System.out.println("suntem aici");
        userService.saveUserToXML(id);
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

    @PutMapping("/updatePassword/{password}")
    public ResponseEntity<AuthenticationResponse> updateUserByPassword(@RequestBody User user, @PathVariable String password){
        return ResponseEntity.ok(userService.changePassword(user, password));
//        User user1 = userService.changePassword(user, password);
//        UserDTO userDTO = modelMapper.map(user1, UserDTO.class);
//        return userDTO;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user){
       return ResponseEntity.ok(userService.register(user));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logIn(@RequestBody UserDTO userDTO){
        log.info("inside postmapp");
        return ResponseEntity.ok(userService.logIn(userDTO.getEmail(), userDTO.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseEntity logOut(@RequestBody UserDTO userDTO){

        User user1 = userService.logOut(userDTO.getEmail());
        UserDTO userDTO1 = modelMapper.map(user1, UserDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO1);
    }

}
