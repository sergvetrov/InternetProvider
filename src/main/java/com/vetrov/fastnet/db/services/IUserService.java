package com.vetrov.fastnet.db.services;

import com.vetrov.fastnet.db.entity.Tariff;
import com.vetrov.fastnet.db.entity.User;

import java.util.List;


public interface IUserService {

    List<User> findAll();

    User find(long id);

    void save(User user);

    void update(User user);

    void remove(int id);

    User findByLogin(String login);

    List<Tariff> findUserTariffs(User user);

    void saveLinksUsersHasTariffs(User user, String[] tariffsId);

    void removeLinksUsersHasTariffs(User user);
}
