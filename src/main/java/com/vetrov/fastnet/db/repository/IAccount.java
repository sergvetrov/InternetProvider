package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.entity.Account;


public interface IAccount extends IEntity<Account> {
    long newNumberContract();
}
