package com.proiectps.BookShop.repository;

import com.proiectps.BookShop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);

}
