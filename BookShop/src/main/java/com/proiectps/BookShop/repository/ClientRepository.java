package com.proiectps.BookShop.repository;

import com.proiectps.BookShop.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findClientByEmailAndPassword(String email, String password);
    Client findClientByEmail(String email);
    Client findClientByPassword(String password);

    void delete(Client client);
}
