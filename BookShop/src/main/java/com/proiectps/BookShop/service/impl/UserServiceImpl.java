package com.proiectps.BookShop.service.impl;

import com.proiectps.BookShop.controller.auth.AuthenticationResponse;
import com.proiectps.BookShop.model.*;
import com.proiectps.BookShop.repository.AdminRepository;
import com.proiectps.BookShop.repository.BookRepository;
import com.proiectps.BookShop.repository.ClientRepository;
import com.proiectps.BookShop.repository.UserRepository;
import com.proiectps.BookShop.service.UserService;
import com.proiectps.BookShop.validator.UserValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final AdminRepository adminRepository;
    @Autowired
    private BookRepository bookRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    private final UserValidator userValidator = new UserValidator();

//    public UserServiceImpl(UserRepository userRepository, ClientRepository clientRepository, AdminRepository adminRepository) {
//        this.userRepository = userRepository;
//        this.clientRepository = clientRepository;
//        this.adminRepository = adminRepository;
//    }

    @Override
    public User findById(Long id) { //aici chiar ma distrez :)))
        return userRepository.findById(id).get();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).get();
    }

    @Override
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

//    @Override
//    public User logIn(String email, String password) {
//        try {
//            User logInUser = findByEmailAndPassword(email, password);
//            userValidator.validateUser(logInUser);
//            logInUser.setUserLogged(true);
//            userRepository.save(logInUser);
//            return logInUser;
//        } catch (WrongAndNullException e) {
//            //throw new RuntimeException(e);
//            System.out.println("Invalid user - email/password incorrect");
//            return null;
//        }
//    }

    @Override
    public AuthenticationResponse logIn(String email, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

//            var user = userRepository.findByEmailAndPassword(email, password)
//                    .orElseThrow();

            var user = userRepository.findByEmail(email)
                    .orElseThrow();
            userValidator.validateUser(user);
            user.setUserLogged(true);
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();

        } catch (Exception | WrongAndNullException exception) {
            log.error("{}", exception);
        }
        log.info("tam[it");
     return null;
    }

    @Override
    public User logOut(String email) {//trebuie modificata
        Optional<User> userLogged = userRepository.findByEmail(email);
        Client clientLogged = new Client();
        List<Client> clientsAux = new ArrayList<Client>();
        if(userLogged.get().getRole() == Role.CLIENT) {
            clientLogged = clientRepository.findClientByEmail(email);
            List<Book> books = clientLogged.getBooks1();
            for(Book b: books){
                for(Client c: b.getClients()){
                    clientsAux.add(c);
                }

                for(Client c: b.getClients()){
                    if(c.equals(clientLogged)){
                        clientsAux.remove(c);
                    }
                }
                b.setClients(clientsAux);
                bookRepository.save(b);
            }
            clientLogged.setBooks1(new ArrayList<Book>());
            clientRepository.save(clientLogged);
        }
        userLogged.get().setUserLogged(false);
        userRepository.save(userLogged.get());

        return userLogged.get();
    }

//    @Override
//    public User register(User user) {
//        try {
//            userValidator.validateUser(user);
//            user.saveUser(adminRepository, clientRepository);
//            System.out.println("Userul s-a inregistrat cu succes!");
//            return userRepository.save(user);
//        } catch (WrongAndNullException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Override
    public AuthenticationResponse register(User user){
        try {
            userValidator.validateUser(user);
            var user1 = User.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role(Role.CLIENT)
                    .build();
            user1.saveUser(adminRepository, clientRepository);
            userRepository.save(user1);
            var jwtToken = jwtService.generateToken(user1);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public AuthenticationResponse registerAdmin(User user){
        try {
            userValidator.validateUser(user);
            var user1 = User.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role(Role.ADMIN)
                    .build();
            user1.saveUser(adminRepository, clientRepository);
            userRepository.save(user1);
            var jwtToken = jwtService.generateToken(user1);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public User changeEmail(User user, String email) { //nu o folosesc
        try {
            userValidator.validateUser(user);
            userValidator.validateEmail(email);
            User updateUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();
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
    public AuthenticationResponse changePassword(User user, String password) { //trebuie modificata
        try {
            userValidator.validatePassword(password);
            Optional<User> updateUser = userRepository.findByEmail(user.getEmail());
            Client updateClient = clientRepository.findClientByEmail(user.getEmail());
            Admin updateAdmin = adminRepository.findAdminByEmail(user.getEmail());
            userValidator.validateUser(updateUser.get());
            updateUser.get().setPassword(passwordEncoder.encode(password));
            if(updateUser.get().getRole() == Role.CLIENT){
                updateClient.setPassword(passwordEncoder.encode(password));
                clientRepository.save(updateClient);
            } else {
                updateAdmin.setPassword(passwordEncoder.encode(password));
                adminRepository.save(updateAdmin);
            }
            userRepository.save(updateUser.get());
            var jwtToken = jwtService.generateToken(updateUser.get());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User deleteUser(User user) { //nu o folosesc
        try {
            userValidator.validateUser(user);
            User deleteUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();
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

    @Override
    public void saveUserToXML(Long id) {
        Optional<User> user = userRepository.findById(id);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(user.get(), new File("Users" + ".xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}
