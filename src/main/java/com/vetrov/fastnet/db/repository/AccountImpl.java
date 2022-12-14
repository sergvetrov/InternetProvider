package com.vetrov.fastnet.db.repository;


import com.vetrov.fastnet.db.builders.AccountQueryBuilder;
import com.vetrov.fastnet.db.builders.QueryBuilder;
import com.vetrov.fastnet.db.entity.Account;

import java.util.List;


public class AccountImpl implements IAccount {
    private static final String CREATE = "INSERT INTO internet_provider.accounts (id, number, balance) VALUES (?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM internet_provider.accounts";
    private static final String GET_BY_ID = "SELECT id, number, balance FROM internet_provider.accounts WHERE id = ?";
    private static final String UPDATE = "UPDATE internet_provider.accounts SET balance = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM internet_provider.accounts WHERE id = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'internet_provider' AND TABLE_NAME = 'users'";
    private static final String GET_MAX_ID = "SELECT MAX(id) FROM accounts";

    private final DBManager instance = DBManager.getInstance();
    private final QueryBuilder<Account> queryBuilder = new AccountQueryBuilder();

    @Override
    public List<Account> getAll() {
        return this.queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public Account getById(long id) {
        return this.queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(Account account) {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        this.queryBuilder.execute(instance, CREATE, id, account.getNumber(), account.getBalance());
    }

    @Override
    public void update(Account account) {
        this.queryBuilder.execute(instance, UPDATE, account.getBalance(), account.getId());
    }

    @Override
    public void delete(long id) {
        this.queryBuilder.execute(instance, DELETE, id);
    }

    @Override
    public long newNumberContract() {
        long accountNumber = 0;
        long id = queryBuilder.getNextAutoIncrement(instance, GET_MAX_ID);
        Account account = getById(id);

        if (account != null) {
            accountNumber = 1 + account.getNumber();
        }

        return accountNumber;
    }
}
