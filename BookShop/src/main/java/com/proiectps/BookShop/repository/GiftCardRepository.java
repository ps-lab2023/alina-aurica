package com.proiectps.BookShop.repository;

import com.proiectps.BookShop.model.GiftCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardRepository extends CrudRepository<GiftCard, Long> {
}
