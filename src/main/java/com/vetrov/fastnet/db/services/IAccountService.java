package com.vetrov.fastnet.db.services;

import com.vetrov.fastnet.db.entity.Account;

import java.util.List;


public interface IAccountService {

    List<Account> findAll();

    Account find(long id);

    void save(Account account);

    void update(Account account);

    void remove(long id);

    long getNumberContract();
}
