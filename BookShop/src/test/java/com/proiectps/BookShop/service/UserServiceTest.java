package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Admin;
import com.proiectps.BookShop.model.Role;
import com.proiectps.BookShop.model.User;
import com.proiectps.BookShop.repository.AdminRepository;
import com.proiectps.BookShop.repository.ClientRepository;
import com.proiectps.BookShop.repository.UserRepository;
import com.proiectps.BookShop.service.impl.AdminServiceImpl;
import com.proiectps.BookShop.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest { //trebuie sa mai fac pt insert (cred ca e register la mine), update, delete
        private static final Long ID = 1L;
        private static final Long ID_NOT = -1L;

        private static final String EMAIL = "mihai.pop@gmail.com";
        private static final String EMAIL2 = "elena.pop@gmail.com";
        private static final String EMAIL_NOT = "mihai.pop@gmail";

        private static final String PASSWORD = "aksjms";
        private static final String PASSWORD_NOT = "ana";

        private static final User USER1 = new User(1L, "Mihai", "Pop", "mihai.pop@gmail.com", "aksjms", Role.CLIENT, false);
        private static final User USER2 = new User(4L, "Mihai", "Popescu", "mihai.popescu@gmail.com", "aksjms", Role.CLIENT, false);

        private static final User USER_NOT = new User(1L, "Mihai", "Anton", "mihai.anton@gmail.com", "alaska", Role.CLIENT, false);

        private UserServiceImpl userServiceImpl;

        @Mock
        private UserRepository userRepository;
        @Mock
        private ClientRepository clientRepository;
        @Mock
        private AdminRepository adminRepository;

        private User user;
        private User user2;
        private User user3;
        private User user4;

        @BeforeEach
        void init(){
                initMocks(this);
                initMocks(this);
                initMocks(this);
                user = new User();
                user.setId(ID);
                user.setFirstName("Mahai");
                user.setLastName("Pop");
                user.setEmail(EMAIL);
                user.setPassword(PASSWORD);
                user.setRole(Role.CLIENT);

                user2 = new User();
                user2.setId(4L);
                user2.setFirstName("Elena");
                user2.setLastName("Pop");
                user2.setEmail(EMAIL2);
                user2.setPassword(PASSWORD);
                user2.setRole(Role.CLIENT);

                User userAux = new User();
                userAux.setId(4L);
                userAux.setFirstName("Elena");
                userAux.setLastName("Pop");
                userAux.setEmail(EMAIL2);
                userAux.setPassword(PASSWORD);
                userAux.setRole(Role.CLIENT);

                user3 = new User();
                user3.setId(ID);
                user3.setFirstName("Mihai");
                user3.setLastName("Pop");
                user3.setEmail(EMAIL2);
                user3.setPassword(PASSWORD);
                user3.setRole(Role.CLIENT);

                user4 = new User();
                user4.setId(ID);
                user4.setFirstName("Mihai");
                user4.setLastName("Pop");
                user4.setEmail(EMAIL2);
                user4.setPassword(PASSWORD);
                user4.setRole(Role.CLIENT);


                when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
                when(userRepository.findByEmail(EMAIL)).thenReturn(user);
                when(userRepository.findByEmailAndPassword(EMAIL, PASSWORD)).thenReturn(user);
                when(userRepository.save(userAux)).thenReturn(user2);
                when(userRepository.save(user3)).thenReturn(user4);
                doNothing().when(userRepository).delete(user);
                when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user4));
        }

        @Test
        void givenExistingId_whenFindById_thenFindOne(){
                userServiceImpl = new UserServiceImpl(userRepository, clientRepository, adminRepository);
                User userAux = new User();
                userAux.setId(ID);
                User user = userServiceImpl.findById(userAux.getId());

                assertNotNull(user);
                assertEquals(ID, user.getId());
        }

        @Test
        void givenNotExistingId_whenFindById_thenThrowExcepsion(){
                when(userRepository.findById(ID_NOT)).thenReturn(null);

                User userAux = new User();
                userAux.setId(ID_NOT);

                Exception exception = assertThrows(NullPointerException.class, () -> {
                        userServiceImpl.findById(userAux.getId());
                });
        }

        @Test
        void givenExistingEmail_whenFindByEmail_thenFindOne(){
                userServiceImpl = new UserServiceImpl(userRepository, clientRepository, adminRepository);
                User userAux = new User();
                userAux.setEmail(EMAIL);
                //System.out.println(userAux);
                User user = userServiceImpl.findByEmail(userAux.getEmail());
                //System.out.println(userServiceImpl.findByEmail(userAux));

                assertNotNull(user);
                assertEquals(EMAIL, user.getEmail());
        }

        @Test
        void givenNotExistingEmail_whenFindByEmail_thenThrowExcepsion(){
                when(userRepository.findByEmail(EMAIL_NOT)).thenReturn(null);

                User userAux = new User();
                userAux.setEmail(EMAIL_NOT);

                Exception exception = assertThrows(NullPointerException.class, () -> {
                        userServiceImpl.findByEmail(userAux.getEmail());
                });
        }

        @Test
        void givenExistingEmailAndPassword_whenFindByEmailAndPassword_thenFindOne(){
                userServiceImpl = new UserServiceImpl(userRepository, clientRepository, adminRepository);
                User userAux = new User();
                userAux.setEmail(EMAIL);
                userAux.setPassword(PASSWORD);
                //System.out.println(userAux);
                User user = userServiceImpl.findByEmailAndPassword(userAux.getEmail(), userAux.getPassword());
                //System.out.println(userServiceImpl.findByEmail(userAux));

                assertNotNull(user);
                assertEquals(EMAIL, user.getEmail());
                assertEquals(PASSWORD, user.getPassword());
        }

        @Test
        void givenNotExistingEmailAndPassword_whenFindByEmailAndPassword_thenThrowExcepsion(){
                when(userRepository.findByEmailAndPassword(EMAIL,PASSWORD_NOT)).thenReturn(null);

                User userAux = new User();
                userAux.setEmail(EMAIL);
                userAux.setPassword(PASSWORD_NOT);

                Exception exception = assertThrows(NullPointerException.class, () -> {
                        userServiceImpl.findByEmailAndPassword(userAux.getEmail(), userAux.getPassword());
                });
        }

        @Test
        void givenExistingUserByLogIn_whenLogIn_thenFindOne(){
                userServiceImpl = new UserServiceImpl(userRepository, clientRepository, adminRepository);
                User userAux = new User();
                userAux.setId(ID);
                userAux.setFirstName("Mihai");
                userAux.setLastName("Pop");
                userAux.setEmail(EMAIL);
                userAux.setPassword(PASSWORD);
                userAux.setRole(Role.CLIENT);
                User user = userServiceImpl.logIn(userAux.getEmail(), userAux.getPassword());
                //System.out.println(userServiceImpl.findByEmail(userAux));

                assertNotNull(user);
                assertEquals(USER1.toString(), user.toString());
        }

        @Test
        void givenNotExistingUserByLogIn_whenLogIn_thenThrowExcepsion(){
                //when(userRepository.findByEmail(EMAIL_NOT)).thenReturn(null);

                User userAux;
                userAux = USER_NOT;

                Exception exception = assertThrows(NullPointerException.class, () -> {
                        userServiceImpl.logIn(userAux.getEmail(), userAux.getPassword());
                });
        }

        @Test
        void givenExistingUser_whenRegister_thenFindOne(){
                userServiceImpl = new UserServiceImpl(userRepository, clientRepository, adminRepository);
                //System.out.println(bookRepository.findByName(NAME));
                //System.out.println(bookRepository.findAllByName(NAME).toString());

                //bookServiceImpl.searchFromName(NAME);

                User userAux = new User();
                userAux.setId(4L);
                userAux.setFirstName("Elena");
                userAux.setLastName("Pop");
                userAux.setEmail(EMAIL2);
                userAux.setPassword(PASSWORD);
                userAux.setRole(Role.CLIENT);

                User user = userServiceImpl.register(userAux);

                assertNotNull(user);
                assertEquals("elena.pop@gmail.com", user.getEmail());
        }

        @Test
        void givenNonExistingBook_whenInsertBook_thenFindFirstOne(){
                User userAux = new User();
                userAux.setId(1L);
                userAux.setEmail(EMAIL2);
                userAux.setPassword(PASSWORD);
                when(userRepository.save(userAux)).thenReturn(null);

                Exception exception = assertThrows(NullPointerException.class, () -> {
                        userServiceImpl.register(userAux);
                });
        }


        @Test
        void givenExistingUser_whenChangeEmail_thenFindOne(){
                userServiceImpl = new UserServiceImpl(userRepository, clientRepository, adminRepository);
                //System.out.println(bookRepository.findByName(NAME));
                //System.out.println(bookRepository.findAllByName(NAME).toString());

                //bookServiceImpl.searchFromName(NAME);

                User userAux = new User();
                userAux.setId(1L);
                userAux.setEmail(EMAIL);
                User user = userServiceImpl.changeEmail(userAux, EMAIL2);

                assertNotNull(user);
                assertEquals(EMAIL2, user.getEmail());
        }

        @Test
        void givenNonExistingUser_whenChangeEmail_thenFindFirstOne(){
                User userAux = new User();
                userAux.setId(8L);
                //Book bookAux2 = new Book();
                //bookAux.setId(bookRepository.findByName(NAME).getId());
                userAux.setEmail(EMAIL_NOT);
                //bookAux.setName(NAME_NOT);
                when(userRepository.save(userAux)).thenReturn(null);

                Exception exception = assertThrows(NullPointerException.class, () -> {
                        userServiceImpl.changeEmail(userAux, EMAIL2);
                });
        }

        @Test
        void doNothingUser_whenDelete(){
                userServiceImpl = new UserServiceImpl(userRepository, clientRepository, adminRepository);
                //System.out.println(bookRepository.findByName(NAME));
                //System.out.println(bookRepository.findAllByName(NAME).toString());

                //bookServiceImpl.searchFromName(NAME);

                User userAux = new User();
                userAux.setId(1L);
                userAux.setEmail(EMAIL);
                userAux.setPassword(PASSWORD);

                User user = userServiceImpl.deleteUser(userAux);

                assertNotNull(user);
                //assertEquals(NAME, book.getName());
        }

        @Test
        void notDoNothingAdmin_whenDelete(){
                User userAux = new User();
                userAux.setId(6L);
                //Book bookAux2 = new Book();
                //bookAux.setId(bookRepository.findByName(NAME).getId());
                userAux.setEmail(EMAIL_NOT);
                //bookAux.setName(NAME_NOT);
                userAux.setPassword(PASSWORD);

                Exception exception = assertThrows(NullPointerException.class, () -> {
                        userServiceImpl.deleteUser(userAux);
                });
        }
}
