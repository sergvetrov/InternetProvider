package com.vetrov.fastnet.db.services;

import com.vetrov.fastnet.db.entity.ContactDetails;

import java.util.List;


public interface IContactDetailsService {

    List<ContactDetails> findAll();

    ContactDetails find(long id);

    void save(ContactDetails account);

    void update(ContactDetails account);

    void remove(int id);
}
