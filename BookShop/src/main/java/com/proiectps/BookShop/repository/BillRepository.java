package com.proiectps.BookShop.repository;

import com.proiectps.BookShop.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

}
