package com.vetrov.fastnet.db.services;

import com.vetrov.fastnet.db.entity.ContactDetails;
import com.vetrov.fastnet.db.repository.ContactDetailsImpl;
import com.vetrov.fastnet.db.repository.IContactDetails;

import java.util.List;


public class ContactDetailsServiceImpl implements IContactDetailsService {
    private final IContactDetails repo = new ContactDetailsImpl();

    @Override
    public List<ContactDetails> findAll() {
        return this.repo.getAll();
    }

    @Override
    public ContactDetails find(long id) {
        return this.repo.getById(id);
    }

    @Override
    public void save(ContactDetails account) {
        this.repo.create(account);
    }

    @Override
    public void update(ContactDetails account) {
        this.repo.update(account);
    }

    @Override
    public void remove(int id) {
        this.repo.delete(id);
    }
}
