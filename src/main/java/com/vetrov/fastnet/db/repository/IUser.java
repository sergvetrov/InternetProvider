package com.vetrov.fastnet.db.repository;

import com.vetrov.fastnet.db.entity.Tariff;
import com.vetrov.fastnet.db.entity.User;

import java.util.List;


public interface IUser extends IEntity<User> {

    User getByLogin(String login);

    List<Tariff> getTariffs(User user);

    void addLinksUsersHasTariffs(User user, String[] tariffsId);

    void deleteLinksUsersHasTariffs(User user);
}
