package com.vetrov.fastnet.db.builders;

import com.vetrov.fastnet.db.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountQueryBuilder extends QueryBuilder<Account> {
    @Override
    public List<Account> getListOfResult(ResultSet rs) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            Account account = new Account();
            setAccountInfo(rs, account);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account getResult(ResultSet rs) throws SQLException {
        Account account = new Account();
        while (rs.next()) {
            setAccountInfo(rs, account);
        }
        return account;
    }

    private void setAccountInfo(ResultSet rs, Account account) throws SQLException {
        account.setId(rs.getLong("id"));
        account.setNumber(rs.getInt("number"));
        account.setBalance(rs.getDouble("balance"));
    }
}
